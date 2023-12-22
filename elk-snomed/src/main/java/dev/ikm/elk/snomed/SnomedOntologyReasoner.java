package dev.ikm.elk.snomed;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;
import dev.ikm.elk.snomed.owlapix.model.OwlxOntology;
import dev.ikm.elk.snomed.owlapix.reasoner.InferenceType;
import dev.ikm.elk.snomed.reasoner.ElkReasoner;
import dev.ikm.elk.snomed.reasoner.ElkReasonerFactory;

public class SnomedOntologyReasoner {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOntologyReasoner.class);

	private SnomedOntology snomedOntology;

	private OwlxOntology ontology;

	private ElkReasoner reasoner;

	private SnomedOntologyReasoner() {
		super();
	}

	public static SnomedOntologyReasoner create(SnomedOntology snomedOntology) {
		SnomedOntologyReasoner sor = new SnomedOntologyReasoner();
		sor.init(snomedOntology);
		return sor;
	}

	private void init(SnomedOntology snomedOntology) {
		this.snomedOntology = snomedOntology;
		ontology = new OwlxOntology();
		for (RoleType rt : this.snomedOntology.getRoleTypes()) {
			process(rt);
		}
		for (Concept con : this.snomedOntology.getConcepts()) {
			process(con);
		}
		reasoner = new ElkReasonerFactory().createReasoner(ontology);
		reasoner.flush();
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY, InferenceType.OBJECT_PROPERTY_HIERARCHY);
	}

	private String getIri(RoleType rt) {
		return "" + rt.getId();
	}

	private void process(RoleType rt) {
		String iri = getIri(rt);
		ElkObjectProperty prop = ontology.getElkObjectProperty(iri);
		HashSet<String> rt_axioms = new HashSet<>();
		for (RoleType sup : rt.getSuperRoleTypes()) {
			ElkAxiom axiom = ontology.getElkSubObjectPropertyOfAxiom(iri, getIri(sup));
			ontology.addAxiom(axiom);
			rt_axioms.add(axiom.toString());
		}
		if (rt.isTransitive()) {
			ElkTransitiveObjectPropertyAxiom axiom = ontology.getElkTransitiveObjectPropertyAxiom(iri);
			ontology.addAxiom(axiom);
			LOG.info("Transitive: " + axiom);
			rt_axioms.add(axiom.toString());
		}
		if (rt.getChained() != null) {
			ElkAxiom axiom = ontology.getElkSubObjectPropertyChainOfAxiom(iri, getIri(rt.getChained()));
			ontology.addAxiom(axiom);
			LOG.info("Chained: " + axiom);
			rt_axioms.add(axiom.toString());
		}
		if (rt.isReflexive()) {
			ElkReflexiveObjectPropertyAxiom axiom = ontology.getElkReflexiveObjectPropertyAxiom(iri);
			ontology.addAxiom(axiom);
			LOG.info("Reflexive: " + axiom);
			rt_axioms.add(axiom.toString());
		}
	}

	private String getIri(Concept con) {
		return "" + con.getId();
	}

	private void process(Concept con) {
		for (Definition def : con.getDefinitions()) {
			process(con, def, false);
		}
		for (Definition def : con.getGciDefinitions()) {
			process(con, def, true);
		}
	}

	private void process(Concept con, Definition def, boolean isGci) {
		List<ElkClass> sups = def.getSuperConcepts().stream().map(sup -> ontology.getElkClass(getIri(sup))).toList();
		List<ElkObjectSomeValuesFrom> roles = def.getUngroupedRoles().stream().map(role -> process(role)).toList();
		List<ElkObjectSomeValuesFrom> groups = def.getRoleGroups().stream().map(role -> process(role)).toList();
		List<ElkClassExpression> exprs = new ArrayList<>();
		exprs.addAll(sups);
		exprs.addAll(roles);
		exprs.addAll(groups);
		ElkClassExpression expr;
		if (exprs.size() == 1) {
			expr = exprs.getFirst();
		} else {
			expr = ontology.getObjectFactory().getObjectIntersectionOf(exprs);
		}
		ElkAxiom axiom = null;
		switch (def.getDefinitionType()) {
		case EquivalentConcept -> axiom = ontology.getEquivalentClassesAxiom(getIri(con), expr);
		case SubConcept -> {
			if (isGci) {
				axiom = ontology.getSubClassOfAxiom(expr, getIri(con));
			} else {
				axiom = ontology.getSubClassOfAxiom(getIri(con), expr);
			}
		}
		}
		ontology.addAxiom(axiom);
	}

	private ElkObjectSomeValuesFrom process(Role role) {
		return ontology.getElkObjectSomeValuesFrom(getIri(role.getRoleType()), getIri(role.getConcept()));
	}

	private ElkObjectSomeValuesFrom process(RoleGroup rg) {
		List<ElkObjectSomeValuesFrom> roles = rg.getRoles().stream().map(role -> process(role)).toList();
		ElkClassExpression expr;
		if (roles.size() == 1) {
			expr = roles.getFirst();
		} else {
			expr = ontology.getObjectFactory().getObjectIntersectionOf(roles);
		}
		return ontology.getElkObjectSomeValuesFrom("" + SnomedIds.role_group, expr);
	}

	private <T> List<T> flatten(Node<T> node) {
		List<T> ret = new ArrayList<>();
		for (T el : node) {
			ret.add(el);
		}
		return ret;
	}

	private <T> Set<T> flatten(Set<? extends Node<T>> nodes) {
		Set<T> ret = new HashSet<>();
		for (Node<T> el : nodes) {
			ret.addAll(flatten(el));
		}
		return ret;
	}

	public Set<ElkObjectProperty> getSuperObjectProperties(RoleType rt) {
		Set<? extends Node<ElkObjectProperty>> sups = reasoner
				.getSuperObjectProperties(ontology.getElkObjectProperty(getIri(rt)), true);
		Set<ElkObjectProperty> ret = flatten(sups);
		return ret;
	}

	public Set<ElkClass> getSuperClasses(Concept con) {
		Set<? extends Node<ElkClass>> sups = reasoner.getSuperClasses(ontology.getElkClass(getIri(con)), true);
		Set<ElkClass> flat = flatten(sups);
		flat.remove(ontology.getOwlThing());
		return flat;
	}

}
