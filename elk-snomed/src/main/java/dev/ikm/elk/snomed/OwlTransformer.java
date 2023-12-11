package dev.ikm.elk.snomed;

import java.util.HashMap;

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

import java.util.Set;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;

public class OwlTransformer {

	HashMap<OWLClass, Concept> concepts = new HashMap<>();

	HashMap<OWLObjectProperty, RoleType> roleTypes = new HashMap<>();

	private Concept getConcept(OWLClass clazz) {
		long id = SnomedOwlOntology.getId(clazz);
		concepts.putIfAbsent(clazz, new Concept(id));
		return concepts.get(clazz);
	}

	private RoleType getRoleType(OWLObjectProperty prop) {
		long id = SnomedOwlOntology.getId(prop);
		roleTypes.putIfAbsent(prop, new RoleType(id));
		return roleTypes.get(prop);
	}

	public SnomedOntology transform(SnomedOwlOntology ontology) {
		for (OWLObjectProperty prop : ontology.getObjectProperties()) {
			getRoleType(prop);
		}
		for (OWLSubObjectPropertyOfAxiom ax : ontology.getOntology().getAxioms(AxiomType.SUB_OBJECT_PROPERTY)) {
			OWLObjectProperty prop1 = ax.getSubProperty().asOWLObjectProperty();
			OWLObjectProperty prop2 = ax.getSuperProperty().asOWLObjectProperty();
			getRoleType(prop1).addSuperRoleType(getRoleType(prop2));
		}
		for (OWLSubPropertyChainOfAxiom ax : ontology.getOntology().getAxioms(AxiomType.SUB_PROPERTY_CHAIN_OF)) {
			if (ax.getPropertyChain().size() != 2)
				throw new UnsupportedOperationException("Unexpected: " + ax);
			OWLObjectProperty prop1 = ax.getPropertyChain().get(0).asOWLObjectProperty();
			OWLObjectProperty prop2 = ax.getPropertyChain().get(1).asOWLObjectProperty();
			OWLObjectProperty sup = ax.getSuperProperty().asOWLObjectProperty();
			if (!prop1.equals(sup))
				throw new UnsupportedOperationException("Unexpected: " + ax);
			if (getRoleType(prop1).getChained() != null)
				throw new UnsupportedOperationException(
						"Two chains for: " + prop1 + " " + ax + " " + getRoleType(prop1).getChained());
			getRoleType(prop1).setChained(getRoleType(prop2));
		}
		for (OWLTransitiveObjectPropertyAxiom ax : ontology.getOntology()
				.getAxioms(AxiomType.TRANSITIVE_OBJECT_PROPERTY)) {
			OWLObjectProperty prop = ax.getProperty().asOWLObjectProperty();
			getRoleType(prop).setTransitive(true);
		}
		for (OWLClass clazz : ontology.getOwlClasses()) {
			Concept concept = getConcept(clazz);
			for (OWLClassAxiom axiom : ontology.getAxioms(clazz)) {
				Definition def = createDefinition(clazz, axiom);
				concept.addDefinition(def);
			}
		}
		return new SnomedOntology(concepts.values(), roleTypes.values());
	}

	private Definition createDefinition(OWLClass concept, OWLClassAxiom axiom) {
		Definition def = new Definition();
		switch (axiom) {
		case OWLEquivalentClassesAxiom x -> def.setDefinitionType(DefinitionType.EquivalentConcept);
		case OWLSubClassOfAxiom x -> def.setDefinitionType(DefinitionType.SubConcept);
		default -> throw new UnsupportedOperationException("Unexpected: " + axiom.getAxiomType());
		}
		processAxiom(def, concept, axiom);
		return def;
	}

	private void processAxiom(Definition def, OWLClass concept, OWLClassAxiom axiom) {
		switch (axiom) {
		case OWLEquivalentClassesAxiom x -> {
			Set<OWLClassExpression> class_exprs = x.getClassExpressionsMinus(concept);
			if (class_exprs.size() != 1)
				throw new UnsupportedOperationException("Unexpected: " + class_exprs.size() + " " + class_exprs);
			OWLClassExpression expr = class_exprs.iterator().next();
			processClassExpression(def, expr);
		}
		case OWLSubClassOfAxiom x -> {
			OWLClassExpression expr = x.getSuperClass();
			processClassExpression(def, expr);
		}
		default -> throw new UnsupportedOperationException("Unexpected: " + axiom.getAxiomType());
		}
	}

	private void processClassExpression(Definition def, OWLClassExpression class_expr) {
		switch (class_expr) {
		case OWLClass x -> {
			Concept sup = getConcept(x);
			def.addSuperConcept(sup);
		}
		case OWLObjectIntersectionOf x -> processIntersection(def, x.getOperands());
		default -> throw new UnsupportedOperationException(
				"Unexpected: " + class_expr + " " + class_expr.getClassExpressionType());
		}
	}

	private void processIntersection(Definition def, Set<OWLClassExpression> class_exprs) {
		for (OWLClassExpression class_expr : class_exprs) {
			switch (class_expr) {
			case OWLClass clazz -> {
				Concept sup = getConcept(clazz);
				def.addSuperConcept(sup);
			}
			case OWLObjectSomeValuesFrom svf -> {
				if (SnomedOwlOntology.getId(svf.getProperty().getNamedProperty()) == SnomedIds.role_group) {
					processRoleGroup(def, svf.getFiller());
				} else {
					Role role = makeRole(svf);
					def.addUngroupedRole(role);
				}
			}
			default -> throw new UnsupportedOperationException(
					"Unexpected: " + class_expr + " " + class_expr.getClassExpressionType());
			}
		}
	}

	private Role makeRole(OWLObjectSomeValuesFrom svf) {
		RoleType roleType = getRoleType(svf.getProperty().asOWLObjectProperty());
		Concept concept = getConcept(svf.getFiller().asOWLClass());
		return new Role(roleType, concept);
	}

	private void processRole(RoleGroup rg, OWLObjectSomeValuesFrom svf) {
		Role role = makeRole(svf);
		rg.addRole(role);
	}

	private void processRoleGroup(Definition def, OWLClassExpression class_expr) {
		switch (class_expr) {
		case OWLObjectSomeValuesFrom svf -> {
			RoleGroup rg = new RoleGroup();
			def.addRoleGroup(rg);
			processRole(rg, svf);
		}
		case OWLObjectIntersectionOf x -> {
			processRoleGroup(def, x.getOperands());
		}
		default -> throw new UnsupportedOperationException(
				"Unexpected: " + class_expr + " " + class_expr.getClassExpressionType());
		}
	}

	private void processRoleGroup(Definition def, Set<OWLClassExpression> class_exprs) {
		RoleGroup rg = new RoleGroup();
		def.addRoleGroup(rg);
		for (OWLClassExpression class_expr : class_exprs) {
			switch (class_expr) {
			case OWLObjectSomeValuesFrom svf -> {
				processRole(rg, svf);
			}
			default -> throw new UnsupportedOperationException(
					"Unexpected: " + class_expr + " " + class_expr.getClassExpressionType());
			}
		}
	}

}
