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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.interfaces.ElkObject.Factory;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.model.ConcreteRoleType;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;
import dev.ikm.elk.snomed.owlapix.model.OwlxOntology;
import dev.ikm.elk.snomed.owlapix.reasoner.InferenceType;
import dev.ikm.elk.snomed.reasoner.ElkReasoner;

public class SnomedOntologyReasoner {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOntologyReasoner.class);

	private SnomedOntology snomedOntology;

	private OwlxOntology ontology;

	private ElkReasoner reasoner;

	private HashMap<Long, List<ElkAxiom>> conceptIdAxiomMap;

	private SnomedOntologyReasoner() {
		super();
	}

	public static SnomedOntologyReasoner create(SnomedOntology snomedOntology) {
		SnomedOntologyReasoner sor = new SnomedOntologyReasoner();
		sor.init(snomedOntology);
		return sor;
	}

	public SnomedOntology getSnomedOntology() {
		return snomedOntology;
	}

	public List<ElkAxiom> getConceptAxioms(long id) {
		return conceptIdAxiomMap.get(id);
	}

	private void init(SnomedOntology snomedOntology) {
		this.snomedOntology = snomedOntology;
		ontology = new OwlxOntology();
		conceptIdAxiomMap = new HashMap<>();
		for (RoleType rt : this.snomedOntology.getRoleTypes()) {
			process(rt);
		}
		for (ConcreteRoleType dt : this.snomedOntology.getConcreteRoleTypes()) {
			process(dt);
		}
		for (Concept con : this.snomedOntology.getConcepts()) {
			process(con);
		}
		reasoner = ElkReasoner.createReasoner(ontology, ontology.getObjectFactory());
		reasoner.flush();
		try {
			reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY, InferenceType.OBJECT_PROPERTY_HIERARCHY);
			// TODO ElkException
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void flush() {
		reasoner.flush();
		try {
			reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
			// TODO ElkException
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String getIri(RoleType rt) {
		return "" + rt.getId();
	}

	public static long getId(ElkObjectProperty clazz) {
		return Long.parseLong(clazz.getIri().toString().substring(1));
	}

	public RoleType getRoleType(ElkObjectProperty clazz) {
		return snomedOntology.getRoleType(getId(clazz));
	}

	private void process(RoleType rt) {
		String iri = getIri(rt);
		ontology.getElkObjectProperty(iri);
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

	private void process(ConcreteRoleType dt) {
		String iri = getIri(dt);
		ontology.getElkDataProperty(iri);
		HashSet<String> dt_axioms = new HashSet<>();
		for (ConcreteRoleType sup : dt.getSuperConcreteRoleTypes()) {
			ElkAxiom axiom = ontology.getElkSubDataPropertyOfAxiom(iri, getIri(sup));
			ontology.addAxiom(axiom);
			dt_axioms.add(axiom.toString());
		}
	}

	private String getIri(ConcreteRoleType dt) {
		return "" + dt.getId();
	}

	private String getIri(Concept con) {
		return "" + con.getId();
	}

	public static long getId(ElkClass clazz) {
		return Long.parseLong(clazz.getIri().toString().substring(1));
	}

	public Concept getConcept(ElkClass clazz) {
		return snomedOntology.getConcept(getId(clazz));
	}

	public void process(Concept con) {
		List<ElkAxiom> axioms = conceptIdAxiomMap.get(con.getId());
		if (axioms != null)
			axioms.forEach(ax -> ontology.removeAxiom(ax));
		conceptIdAxiomMap.put(con.getId(), new ArrayList<>());
		for (Definition def : con.getDefinitions()) {
			process(con, def, false);
		}
		for (Definition def : con.getGciDefinitions()) {
			process(con, def, true);
		}
	}

	private void process(Concept con, Definition def, boolean isGci) {
		List<ElkClass> sups = def.getSuperConcepts().stream().map(sup -> ontology.getElkClass(getIri(sup))).toList();
		List<ElkObjectSomeValuesFrom> roles = def.getUngroupedRoles().stream().map(x -> process(x)).toList();
		List<ElkDataHasValue> props = def.getUngroupedConcreteRoles().stream().map(this::process).toList();
		List<ElkObjectSomeValuesFrom> groups = def.getRoleGroups().stream().map(x -> process(x)).toList();
		List<ElkClassExpression> exprs = new ArrayList<>();
		exprs.addAll(sups);
		exprs.addAll(roles);
		exprs.addAll(props);
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
		conceptIdAxiomMap.get(con.getId()).add(axiom);
	}

	private ElkObjectSomeValuesFrom process(Role role) {
		return ontology.getElkObjectSomeValuesFrom(getIri(role.getRoleType()), getIri(role.getConcept()));
	}

	private ElkObjectSomeValuesFrom process(RoleGroup rg) {
		List<ElkObjectSomeValuesFrom> roles = rg.getRoles().stream().map(role -> process(role)).toList();
		List<ElkDataHasValue> props = rg.getConcreteRoles().stream().map(x -> process(x)).toList();
		ArrayList<ElkClassExpression> exprs = new ArrayList<>();
		exprs.addAll(roles);
		exprs.addAll(props);
		ElkClassExpression expr;
		if (exprs.size() == 1) {
			expr = exprs.getFirst();
		} else {
			expr = ontology.getObjectFactory().getObjectIntersectionOf(exprs);
		}
		return ontology.getElkObjectSomeValuesFrom("" + SnomedIds.role_group, expr);
	}

	private ElkDataHasValue process(ConcreteRole concreteRole) {
		Factory f = ontology.getObjectFactory();
		ElkDatatype datatype = switch (concreteRole.getValueType()) {
		case Decimal -> f.getXsdDecimal();
		case Double -> f.getXsdDouble();
		case Float -> f.getXsdFloat();
		case Integer -> f.getXsdInteger();
		case String -> f.getXsdString();
		};
		ElkLiteral literal = f.getLiteral(concreteRole.getValue(), datatype);
		return ontology.getDataHasValue(getIri(concreteRole.getConcreteRoleType()), literal);
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
		return getSuperObjectProperties(rt, true);
	}

	public Set<ElkObjectProperty> getSuperObjectProperties(RoleType rt, boolean direct) {
		try {
			Set<? extends Node<ElkObjectProperty>> sups = reasoner
					.getSuperObjectProperties(ontology.getElkObjectProperty(getIri(rt)), direct);
			Set<ElkObjectProperty> ret = flatten(sups);
			ret.remove(ontology.getOwlTopObjectProperty());
			return ret;
			// TODO ElkException
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Set<RoleType> getSuperRoleTypes(RoleType rt) {
		return getSuperRoleTypes(rt, true);
	}

	public Set<RoleType> getSuperRoleTypes(RoleType rt, boolean direct) {
		return getSuperObjectProperties(rt, direct).stream().map(this::getRoleType)
				.collect(Collectors.toCollection(HashSet::new));
	}

	public Set<Long> getSuperRoleTypes(long id, boolean direct) {
		RoleType rt = snomedOntology.getRoleType(id);
		if (rt == null)
			return null;
		return getSuperRoleTypes(rt, direct).stream().map(RoleType::getId)
				.collect(Collectors.toCollection(HashSet::new));
	}

	public HashMap<Long, Set<Long>> getSuperRoleTypes(boolean direct) {
		HashMap<Long, Set<Long>> superRoleTypes = new HashMap<>();
		for (RoleType rt : snomedOntology.getRoleTypes()) {
			long id = rt.getId();
			superRoleTypes.put(id, getSuperRoleTypes(id, direct));
		}
		return superRoleTypes;
	}

	public Set<ElkClass> getSuperClasses(Concept con) {
		return getSuperClasses(con, true);
	}

	public Set<ElkClass> getSuperClasses(Concept con, boolean direct) {
		try {
			Set<? extends Node<ElkClass>> sups = reasoner.getSuperClasses(ontology.getElkClass(getIri(con)), direct);
			Set<ElkClass> flat = flatten(sups);
			flat.remove(ontology.getOwlThing());
			return flat;
			// TODO ElkException
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Set<Concept> getSuperConcepts(Concept con) {
		return getSuperConcepts(con, true);
	}

	public Set<Concept> getSuperConcepts(Concept con, boolean direct) {
		return getSuperClasses(con, direct).stream().map(this::getConcept)
				.collect(Collectors.toCollection(HashSet::new));
	}

	public Set<Long> getSuperConcepts(long id) {
		return getSuperConcepts(id, true);
	}

	public Set<Long> getSuperConcepts(long id, boolean direct) {
		Concept con = snomedOntology.getConcept(id);
		if (con == null)
			return null;
		return getSuperConcepts(con, direct).stream().map(Concept::getId)
				.collect(Collectors.toCollection(HashSet::new));
	}

	public HashMap<Long, Set<Long>> getSuperConcepts() {
		HashMap<Long, Set<Long>> superConcepts = new HashMap<>();
		for (Concept concept : snomedOntology.getConcepts()) {
			long id = concept.getId();
			superConcepts.put(id, getSuperConcepts(id));
		}
		return superConcepts;
	}

	public Set<ElkClass> getSubClasses(Concept con) {
		return getSubClasses(con, true);
	}

	public Set<ElkClass> getSubClasses(Concept con, boolean direct) {
		try {
			Set<? extends Node<ElkClass>> subs = reasoner.getSubClasses(ontology.getElkClass(getIri(con)), direct);
			Set<ElkClass> flat = flatten(subs);
			flat.remove(ontology.getOwlNothing());
			return flat;
			// TODO ElkException
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Set<Concept> getSubConcepts(Concept con) {
		return getSubConcepts(con, true);
	}

	public Set<Concept> getSubConcepts(Concept con, boolean direct) {
		return getSubClasses(con, direct).stream().map(this::getConcept).collect(Collectors.toCollection(HashSet::new));
	}

	public Set<Long> getSubConcepts(long id) {
		return getSubConcepts(id, true);
	}

	public Set<Long> getSubConcepts(long id, boolean direct) {
		Concept con = snomedOntology.getConcept(id);
		if (con == null)
			return null;
		return getSubConcepts(con, direct).stream().map(Concept::getId).collect(Collectors.toCollection(HashSet::new));
	}

	public Set<ElkClass> getEquivalentClasses(Concept con) {
		try {
			Node<ElkClass> eqs = reasoner.getEquivalentClasses(ontology.getElkClass(getIri(con)));
			Set<ElkClass> flat = new HashSet<>(flatten(eqs));
			flat.remove(ontology.getOwlThing());
			flat.remove(ontology.getOwlNothing());
			return flat;
			// TODO ElkException
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Set<Concept> getEquivalentConcepts(Concept con) {
		return getEquivalentClasses(con).stream().map(this::getConcept).collect(Collectors.toCollection(HashSet::new));
	}

	public Set<Long> getEquivalentConcepts(long id) {
		Concept con = snomedOntology.getConcept(id);
		if (con == null)
			return null;
		return getEquivalentConcepts(con).stream().map(Concept::getId).collect(Collectors.toCollection(HashSet::new));
	}

}
