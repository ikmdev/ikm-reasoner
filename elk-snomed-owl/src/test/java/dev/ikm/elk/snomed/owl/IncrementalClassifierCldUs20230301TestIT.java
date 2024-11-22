package dev.ikm.elk.snomed.owl;

/*-
 * #%L
 * ELK Integration with SNOMED using OWL API
 * %%
 * Copyright (C) 2023 - 2024 Integrated Knowledge Management
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.SnomedIsa;

@TestInstance(Lifecycle.PER_CLASS)
public class IncrementalClassifierCldUs20230301TestIT extends SnomedTestBase implements SnomedVersionUs {

	private static final Logger LOG = LoggerFactory.getLogger(IncrementalClassifierCldUs20230301TestIT.class);

	@Override
	public String getVersion() {
		return "20230301";
	}

	@Override
	public String getInternationalVersion() {
		return "20221231";
	}

	// 413839001 |Chronic lung disease (disorder)|
	public static final long cld_id = 413839001;

	private SnomedIsa equivalent_classes_isa;

	private SnomedIsa sub_class_of_isa;

	@BeforeAll
	public void init() throws Exception {
		LOG.info("Init");
		filesExist();
		equivalent_classes_isa = classifyEquivalentClasses();
		sub_class_of_isa = classifySubClassOf();
	}

	private OWLClassAxiom getCldAxiom(SnomedOwlOntology ontology) {
		Set<OWLClassAxiom> axioms = ontology.getAxioms(ontology.getOwlClass(cld_id));
		assertEquals(1, axioms.size());
		return axioms.iterator().next();
	}

	private OWLClassExpression getCldRhs(SnomedOwlOntology ontology) {
		OWLClassAxiom ax = getCldAxiom(ontology);
		return switch (ax) {
		case OWLSubClassOfAxiom x -> x.getSuperClass();
		case OWLEquivalentClassesAxiom x -> {
			Set<OWLClassExpression> exprs = x.getClassExpressionsMinus(ontology.getOwlClass(cld_id));
			assertEquals(1, exprs.size());
			yield exprs.iterator().next();
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + ax);
		};
	}

	public SnomedOwlOntology loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);

		return ontology;
	}

	private HashMap<Long, Set<Long>> getSuperClassMap(SnomedOwlOntology ontology) {
		HashMap<Long, Set<Long>> superConcepts = new HashMap<>();
		for (OWLClass clazz : ontology.getOntology().getClassesInSignature()) {
			long id = SnomedOwlOntology.getId(clazz);
			superConcepts.put(id, ontology.getSuperClasses(id));
		}
		return superConcepts;
	}

	private void toEquivalentClasses(SnomedOwlOntology ontology) throws Exception {
		OWLClassAxiom ax = getCldAxiom(ontology);
		OWLClassExpression rhs = getCldRhs(ontology);
		LOG.info("Remove: " + ax);
		ontology.getOntologyManager().removeAxiom(ontology.getOntology(), ax);
		ax = ontology.getDataFactory().getOWLEquivalentClassesAxiom(ontology.getOwlClass(cld_id), rhs);
		LOG.info("Add: " + ax);
		ontology.getOntologyManager().addAxiom(ontology.getOntology(), ax);
	}

	private SnomedOwlOntology ontologyEquivalentClasses() throws Exception {
		SnomedOwlOntology ontology = loadOntology();
		toEquivalentClasses(ontology);
		return ontology;
	}

	private SnomedIsa classifyEquivalentClasses() throws Exception {
		SnomedOwlOntology ontology = ontologyEquivalentClasses();
		ontology.classify();
		HashMap<Long, Set<Long>> supers = getSuperClassMap(ontology);
		return SnomedIsa.init(supers);
	}

	private void toSubClassOf(SnomedOwlOntology ontology) throws Exception {
		OWLClassAxiom ax = getCldAxiom(ontology);
		OWLClassExpression rhs = getCldRhs(ontology);
		LOG.info("Remove: " + ax);
		ontology.getOntologyManager().removeAxiom(ontology.getOntology(), ax);
		ax = ontology.getDataFactory().getOWLSubClassOfAxiom(ontology.getOwlClass(cld_id), rhs);
		LOG.info("Add: " + ax);
		ontology.getOntologyManager().addAxiom(ontology.getOntology(), ax);
	}

	private SnomedOwlOntology ontologySubClassOf() throws Exception {
		SnomedOwlOntology ontology = loadOntology();
		toSubClassOf(ontology);
		return ontology;
	}

	private SnomedIsa classifySubClassOf() throws Exception {
		SnomedOwlOntology ontology = ontologySubClassOf();
		ontology.classify();
		HashMap<Long, Set<Long>> supers = getSuperClassMap(ontology);
		return SnomedIsa.init(supers);
	}

	@Test
	public void eqToSub() throws Exception {
		LOG.info("eqToSub");
		SnomedOwlOntology ontology = ontologyEquivalentClasses();
		ontology.classify();
		assertEquals(equivalent_classes_isa.getParentsMap(), getSuperClassMap(ontology));
		toSubClassOf(ontology);
		ontology.getReasoner().flush();
		assertEquals(sub_class_of_isa.getParentsMap(), getSuperClassMap(ontology));
	}

	@Test
	public void subToEq() throws Exception {
		LOG.info("subToEq");
		SnomedOwlOntology ontology = ontologySubClassOf();
		ontology.classify();
		assertEquals(sub_class_of_isa.getParentsMap(), getSuperClassMap(ontology));
		toEquivalentClasses(ontology);
		ontology.getReasoner().flush();
		assertEquals(equivalent_classes_isa.getParentsMap(), getSuperClassMap(ontology));
	}

	@Test
	public void subToEqToSub() throws Exception {
		LOG.info("subToEqToSub");
		SnomedOwlOntology ontology = ontologySubClassOf();
		ontology.classify();
		assertEquals(sub_class_of_isa.getParentsMap(), getSuperClassMap(ontology));
		toEquivalentClasses(ontology);
		ontology.getReasoner().flush();
		assertEquals(equivalent_classes_isa.getParentsMap(), getSuperClassMap(ontology));
		toSubClassOf(ontology);
		ontology.getReasoner().flush();
		assertEquals(sub_class_of_isa.getParentsMap(), getSuperClassMap(ontology));
	}

}
