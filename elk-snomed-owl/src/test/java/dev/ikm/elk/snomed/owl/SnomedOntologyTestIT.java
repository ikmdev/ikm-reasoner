package dev.ikm.elk.snomed.owl;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 Integrated Knowledge Management
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.SnomedIds;
import dev.ikm.elk.snomed.SnomedIsa;

public class SnomedOntologyTestIT extends SnomedTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOntologyTestIT.class);

	protected String getVersion() {
		return "20210301";
	}

	@Test
	public void readAxioms() throws Exception {
		List<String> axioms = SnomedOwlOntology.readAxioms(axioms_file);
		assertEquals(362861, axioms.size());
		axioms.stream().map(ax -> ax.substring(0, ax.indexOf("("))).distinct().sorted().forEach(LOG::info);
	}

	@Test
	public void readOntology() throws Exception {
		List<String> axioms = SnomedOwlOntology.readOntology(axioms_file);
		assertEquals(362862, axioms.size());
		axioms.stream().limit(10).forEach(LOG::info);
		LOG.info("...");
		axioms.stream().skip(axioms.size() - 5).forEach(LOG::info);
	}

	@Test
	public void loadOntology() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		assertEquals(362853, ontology.getOntology().getAxiomCount());
		assertEquals(362853, ontology.getAxioms().size());
		assertEquals(362853, ontology.getOntology().getLogicalAxiomCount());
		assertEquals(361462, ontology.getOntology().getSignature().size());
		assertEquals(361331, ontology.getOwlClasses().size());
		assertEquals(131, ontology.getOwlObjectProperties().size());
		assertEquals(0, ontology.getOntology().getDataPropertiesInSignature().size());
		assertEquals(0, ontology.getOntology().getDatatypesInSignature().size());
		Set<OWLEntity> sig = ontology.getOntology().getSignature();
		sig.removeAll(ontology.getOwlClasses());
		sig.removeAll(ontology.getOwlObjectProperties());
		sig.removeAll(ontology.getOntology().getDataPropertiesInSignature());
		sig.stream().forEach(x -> LOG.info("In sig: " + x + " " + x.getClass()));
		sig.removeAll(ontology.getOntology().getDatatypesInSignature());
		assertEquals(0, sig.size());
		assertEquals(1200,
				ontology.getOwlClasses().stream().filter(x -> ontology.getAxioms(x).size() != 1).toList().size());
		int gci_cnt = 0;
		for (OWLAxiom axiom : ontology.getAxioms()) {
			switch (axiom) {
			case OWLEquivalentClassesAxiom x -> {
				assertEquals(2, x.getClassExpressions().size());
				List<OWLClass> classes = x.getClassExpressions().stream()
						.filter(expr -> expr.isClassExpressionLiteral()).map(expr -> expr.asOWLClass()).toList();
				assertEquals(1, classes.size());
				assertTrue(ontology.getAxioms(classes.getFirst()).contains(axiom));
			}
			case OWLSubClassOfAxiom x -> {
				if (x.getSubClass().isClassExpressionLiteral()) {
					assertTrue(ontology.getAxioms(x.getSubClass().asOWLClass()).contains(axiom));
				} else {
//					LOG.info("GCI: " + x);
					gci_cnt++;
					assertTrue(x.getSuperClass().isClassExpressionLiteral());
					assertFalse(ontology.getAxioms(x.getSuperClass().asOWLClass()).contains(axiom));
				}
			}
			case OWLSubObjectPropertyOfAxiom x -> {
			}
			case OWLTransitiveObjectPropertyAxiom x -> {
			}
			case OWLSubPropertyChainOfAxiom x -> {
			}
			case OWLReflexiveObjectPropertyAxiom x -> {
			}
			default -> throw new UnsupportedOperationException("Unexpected: " + axiom.getAxiomType());
			}
		}
		assertEquals(161, gci_cnt);
		assertEquals(161, ontology.getGciAxioms().size());
		assertEquals(161, ontology.getOwlClasses().stream().mapToInt(x -> ontology.getGciAxioms(x).size()).sum());
	}

	@Test
	public void classify() throws Exception {
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
	}

	@Test
	public void isas() throws Exception {
		TreeSet<Long> misses = new TreeSet<>();
		int miss_cnt = 0;
		SnomedIsa isas = SnomedIsa.init(rels_file);
		SnomedOwlOntology ontology = SnomedOwlOntology.createOntology();
		ontology.loadOntology(axioms_file);
		ontology.classify();
		for (OWLClass clazz : ontology.getOntology().getClassesInSignature()) {
			long id = SnomedOwlOntology.getId(clazz);
			Set<Long> sups = ontology.getSuperClasses(id);
			Set<Long> parents = isas.getParents(id);
			if (id == SnomedIds.root) {
				assertTrue(parents.isEmpty());
			} else {
				assertNotNull(parents);
			}
			if (!parents.equals(sups)) {
				misses.add(id);
				miss_cnt++;
			}
		}
		assertEquals(0, miss_cnt);
	}

}
