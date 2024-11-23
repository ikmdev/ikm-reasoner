package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration with SNOMED
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

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.model.ConcreteRoleType;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;
import dev.ikm.elk.snomed.owlel.OwlElOntology;
import dev.ikm.elk.snomed.owlel.model.OwlElClass;
import dev.ikm.elk.snomed.owlel.model.OwlElClassExpression;
import dev.ikm.elk.snomed.owlel.model.OwlElDataHasValue;
import dev.ikm.elk.snomed.owlel.model.OwlElDataProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElEquivalentClasses;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectIntersectionOf;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectPropertyChain;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectSomeValuesFrom;
import dev.ikm.elk.snomed.owlel.model.OwlElReflexiveObjectProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElSubClassOf;
import dev.ikm.elk.snomed.owlel.model.OwlElSubDataPropertyOf;
import dev.ikm.elk.snomed.owlel.model.OwlElSubObjectPropertyOf;
import dev.ikm.elk.snomed.owlel.model.OwlElTransitiveObjectProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElTypedLiteral;

public class OwlElTransformer {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(OwlElTransformer.class);

	HashMap<OwlElClass, Concept> concepts = new HashMap<>();

	HashMap<OwlElObjectProperty, RoleType> roleTypes = new HashMap<>();

	HashMap<OwlElDataProperty, ConcreteRoleType> dataPropertyTypes = new HashMap<>();

	private static long toLong(String iri) {
		return Long.parseLong(iri.replaceFirst("^.*:", ""));
	}

	public static long getId(OwlElClass clazz) {
		return toLong(clazz.getIri());
	}

	public static long getId(OwlElDataProperty prop) {
		return toLong(prop.getIri());
	}

	public static long getId(OwlElObjectProperty prop) {
		return toLong(prop.getIri());
	}

	private Concept getConcept(OwlElClass clazz) {
		long id = getId(clazz);
		concepts.putIfAbsent(clazz, new Concept(id));
		return concepts.get(clazz);
	}

	private RoleType getRoleType(OwlElObjectProperty prop) {
		long id = getId(prop);
		roleTypes.putIfAbsent(prop, new RoleType(id));
		return roleTypes.get(prop);
	}

	private ConcreteRoleType getConcreteRoleType(OwlElDataProperty prop) {
		long id = getId(prop);
		dataPropertyTypes.putIfAbsent(prop, new ConcreteRoleType(id));
		return dataPropertyTypes.get(prop);
	}

	public SnomedOntology transform(OwlElOntology ontology) {
		for (OwlElObjectProperty prop : ontology.getObjectProperties()) {
			getRoleType(prop);
		}
		for (OwlElSubObjectPropertyOf ax : ontology.getAxioms(OwlElSubObjectPropertyOf.class)) {
			if (ax.getSubProperty() instanceof OwlElObjectPropertyChain) {
				OwlElObjectPropertyChain chain = (OwlElObjectPropertyChain) ax.getSubProperty();
				OwlElObjectProperty prop1 = (OwlElObjectProperty) chain.getExpression1();
				OwlElObjectProperty prop2 = (OwlElObjectProperty) chain.getExpression2();
				OwlElObjectProperty sup = (OwlElObjectProperty) ax.getSuperProperty();
				if (!prop1.equals(sup))
					throw new UnsupportedOperationException("Unexpected: " + ax);
				if (getRoleType(prop1).getChained() != null)
					throw new UnsupportedOperationException(
							"Two chains for: " + prop1 + " " + ax + " " + getRoleType(prop1).getChained());
				getRoleType(prop1).setChained(getRoleType(prop2));
			} else {
				OwlElObjectProperty sub = (OwlElObjectProperty) ax.getSubProperty();
				OwlElObjectProperty sup = (OwlElObjectProperty) ax.getSuperProperty();
				getRoleType(sub).addSuperRoleType(getRoleType(sup));
			}
		}
		for (OwlElTransitiveObjectProperty ax : ontology.getAxioms(OwlElTransitiveObjectProperty.class)) {
			OwlElObjectProperty prop = (OwlElObjectProperty) ax.getProperty();
			getRoleType(prop).setTransitive(true);
		}
		for (OwlElReflexiveObjectProperty ax : ontology.getAxioms(OwlElReflexiveObjectProperty.class)) {
			OwlElObjectProperty prop = (OwlElObjectProperty) ax.getProperty();
			getRoleType(prop).setReflexive(true);
		}
		for (OwlElDataProperty prop : ontology.getDataProperties()) {
			getConcreteRoleType(prop);
		}
		for (OwlElSubDataPropertyOf ax : ontology.getAxioms(OwlElSubDataPropertyOf.class)) {
			OwlElDataProperty sub = (OwlElDataProperty) ax.getSubProperty();
			OwlElDataProperty sup = (OwlElDataProperty) ax.getSuperProperty();
			getConcreteRoleType(sub).addSuperConcreteRoleType(getConcreteRoleType(sup));
		}
		for (OwlElEquivalentClasses ax : ontology.getAxioms(OwlElEquivalentClasses.class)) {
			OwlElClass clazz = (OwlElClass) ax.getExpression1();
			Concept concept = getConcept(clazz);
			Definition def = new Definition();
			def.setDefinitionType(DefinitionType.EquivalentConcept);
			concept.addDefinition(def);
			processClassExpression(def, ax.getExpression2());
		}
		for (OwlElSubClassOf ax : ontology.getAxioms(OwlElSubClassOf.class)) {
			if (ax.getSubClass() instanceof OwlElClass) {
				OwlElClass clazz = (OwlElClass) ax.getSubClass();
				Concept concept = getConcept(clazz);
				Definition def = new Definition();
				def.setDefinitionType(DefinitionType.SubConcept);
				concept.addDefinition(def);
				processClassExpression(def, ax.getSuperClass());
			} else {
				// GCI
				OwlElClass clazz = (OwlElClass) ax.getSuperClass();
				Concept concept = getConcept(clazz);
				Definition def = new Definition();
				def.setDefinitionType(DefinitionType.SubConcept);
				concept.addGciDefinition(def);
				processClassExpression(def, ax.getSubClass());
			}
		}
		return new SnomedOntology(concepts.values(), roleTypes.values(), dataPropertyTypes.values());
	}

//	private Definition createDefinition(OwlElClass concept, OwlElClassAxiom axiom, boolean isGci) {
//		Definition def = new Definition();
//		switch (axiom) {
//		case OwlElEquivalentClasses x -> def.setDefinitionType(DefinitionType.EquivalentConcept);
//		case OwlElSubClassOf x -> def.setDefinitionType(DefinitionType.SubConcept);
//		default -> throw new UnsupportedOperationException("Unexpected: " + axiom);
//		}
//		processAxiom(def, concept, axiom, isGci);
//		return def;
//	}

//	private void processAxiom(Definition def, OwlElClass concept, OwlElClassAxiom axiom, boolean isGci) {
//		switch (axiom) {
//		case OwlElEquivalentClasses x -> {
//			if (isGci)
//				throw new UnsupportedOperationException("Unexpected: GCI for " + axiom);
//			OwlElClassExpression expr = x.getExpression2();
//			processClassExpression(def, expr);
//		}
//		case OwlElSubClassOf x -> {
//			OwlElClassExpression expr = (isGci ? x.getSubClass() : x.getSuperClass());
//			processClassExpression(def, expr);
//		}
//		default -> throw new UnsupportedOperationException("Unexpected: " + axiom);
//		}
//	}

	private void processClassExpression(Definition def, OwlElClassExpression class_expr) {
		switch (class_expr) {
		case OwlElClass x -> {
			Concept sup = getConcept(x);
			def.addSuperConcept(sup);
		}
		case OwlElObjectIntersectionOf x -> processIntersection(def, x.getExpressions());
		default -> throw new UnsupportedOperationException("Unexpected: " + class_expr);
		}
	}

	private void processIntersection(Definition def, List<OwlElClassExpression> class_exprs) {
		for (OwlElClassExpression class_expr : class_exprs) {
			switch (class_expr) {
			case OwlElClass clazz -> {
				Concept sup = getConcept(clazz);
				def.addSuperConcept(sup);
			}
			case OwlElObjectSomeValuesFrom svf -> {
				if (getId((OwlElObjectProperty) svf.getProperty()) == SnomedIds.role_group) {
					processRoleGroup(def, svf.getFiller());
				} else {
					Role role = makeRole(svf);
					def.addUngroupedRole(role);
				}
			}
			case OwlElDataHasValue dhv -> {
				ConcreteRole data_property = makeDataProperty(dhv);
				def.addUngroupedConcreteRole(data_property);
			}
			default -> throw new UnsupportedOperationException("Unexpected: " + class_expr);
			}
		}
	}

	private Role makeRole(OwlElObjectSomeValuesFrom svf) {
		RoleType roleType = getRoleType((OwlElObjectProperty) svf.getProperty());
		Concept concept = getConcept((OwlElClass) svf.getFiller());
		return new Role(roleType, concept);
	}

	private ConcreteRole makeDataProperty(OwlElDataHasValue dhv) {
		ConcreteRoleType dataPropertyType = getConcreteRoleType((OwlElDataProperty) dhv.getProperty());
		OwlElTypedLiteral value = dhv.getFiller();
		String datatype = value.getDatatype();
		ConcreteRole.ValueType value_type = switch (datatype) {
		case "xsd:integer" -> ConcreteRole.ValueType.Integer;
		case "xsd:decimal" -> ConcreteRole.ValueType.Decimal;
		default -> throw new UnsupportedOperationException("Unexpected value: " + datatype);
		};
		return new ConcreteRole(dataPropertyType, value.getLiteral(), value_type);
	}

	private void processRole(RoleGroup rg, OwlElObjectSomeValuesFrom svf) {
		Role role = makeRole(svf);
		rg.addRole(role);
	}

	private void processRoleGroup(Definition def, OwlElClassExpression class_expr) {
		switch (class_expr) {
		case OwlElObjectSomeValuesFrom svf -> {
			RoleGroup rg = new RoleGroup();
			def.addRoleGroup(rg);
			processRole(rg, svf);
		}
		// TODO Should add data property, but this never happens, yet...
		case OwlElObjectIntersectionOf x -> {
			processRoleGroup(def, x.getExpressions());
		}
		default -> throw new UnsupportedOperationException("Unexpected: " + class_expr);
		}
	}

	private void processRoleGroup(Definition def, List<OwlElClassExpression> class_exprs) {
		RoleGroup rg = new RoleGroup();
		def.addRoleGroup(rg);
		for (OwlElClassExpression class_expr : class_exprs) {
			switch (class_expr) {
			case OwlElObjectSomeValuesFrom svf -> {
				processRole(rg, svf);
			}
			case OwlElDataHasValue dhv -> {
				ConcreteRole data_property = makeDataProperty(dhv);
				rg.addConcreteRole(data_property);
			}
			default -> throw new UnsupportedOperationException("Unexpected: " + class_expr);
			}
		}
	}

}
