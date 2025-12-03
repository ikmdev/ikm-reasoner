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

import dev.ikm.elk.snomed.owlapix.reasoner.InferenceType;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.primitive.LongObjectMaps;
import org.eclipse.collections.api.factory.primitive.LongSets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.set.primitive.MutableLongSet;
import org.semanticweb.elk.owl.interfaces.*;
import dev.ikm.elk.snomed.reasoner.ElkReasoner;
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

public class SnomedOntologyReasoner {

	private static final Logger LOG = LoggerFactory.getLogger(SnomedOntologyReasoner.class);

	protected SnomedOntology snomedOntology;

	private OwlxOntology ontology;

	private ElkReasoner reasoner;

	private MutableLongObjectMap<MutableList<ElkAxiom>> conceptIdAxiomMap;

	protected SnomedOntologyReasoner() {
		super();
	}

	public static SnomedOntologyReasoner create(SnomedOntology snomedOntology) {
		SnomedOntologyReasoner sor = new SnomedOntologyReasoner();
		sor.init(snomedOntology);
		sor.computeInferences();
		return sor;
	}

	public SnomedOntology getSnomedOntology() {
		return snomedOntology;
	}

	public MutableList<ElkAxiom> getConceptAxioms(long id) {
		return conceptIdAxiomMap.get(id);
	}

	protected void init(SnomedOntology snomedOntology) {
		this.snomedOntology = snomedOntology;
		ontology = new OwlxOntology();
		conceptIdAxiomMap = LongObjectMaps.mutable.empty();
		process();
	}

	protected void process() {
		for (RoleType rt : this.snomedOntology.getRoleTypes()) {
			process(rt);
		}
		for (ConcreteRoleType dt : this.snomedOntology.getConcreteRoleTypes()) {
			process(dt);
		}
		for (Concept con : this.snomedOntology.getConcepts()) {
			process(con);
		}
	}

	protected void computeInferences() {
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

	protected void process(RoleType rt) {
		String iri = getIri(rt);
		ontology.getElkObjectProperty(iri);
		for (RoleType sup : rt.getSuperRoleTypes()) {
			ElkAxiom axiom = ontology.getElkSubObjectPropertyOfAxiom(iri, getIri(sup));
			ontology.addAxiom(axiom);
		}
		if (rt.isTransitive()) {
			ElkTransitiveObjectPropertyAxiom axiom = ontology.getElkTransitiveObjectPropertyAxiom(iri);
			ontology.addAxiom(axiom);
			LOG.info("Transitive: " + axiom);
		}
		if (rt.getChained() != null) {
			ElkAxiom axiom = ontology.getElkSubObjectPropertyChainOfAxiom(iri, getIri(rt.getChained()));
			ontology.addAxiom(axiom);
			LOG.info("Chained: " + axiom);
		}
		if (rt.isReflexive()) {
			ElkReflexiveObjectPropertyAxiom axiom = ontology.getElkReflexiveObjectPropertyAxiom(iri);
			ontology.addAxiom(axiom);
			LOG.info("Reflexive: " + axiom);
		}
	}

	protected void process(ConcreteRoleType crt) {
		String iri = getIri(crt);
		ontology.getElkDataProperty(iri);
		for (ConcreteRoleType sup : crt.getSuperConcreteRoleTypes()) {
			ElkAxiom axiom = ontology.getElkSubDataPropertyOfAxiom(iri, getIri(sup));
			ontology.addAxiom(axiom);
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

	private void removeAxioms(Concept con) {
		MutableList<ElkAxiom> axioms = conceptIdAxiomMap.get(con.getId());
		if (axioms != null)
			axioms.forEach(ax -> ontology.removeAxiom(ax));
		conceptIdAxiomMap.remove(con.getId());
	}

	public void processDelete(Concept con) {
		removeAxioms(con);
	}

	public void processUpdate(Concept con) {
		process(con);
	}

	protected void process(Concept con) {
		removeAxioms(con);
		conceptIdAxiomMap.put(con.getId(), Lists.mutable.empty());
		for (Definition def : con.getDefinitions()) {
			process(con, def, false);
		}
		for (Definition def : con.getGciDefinitions()) {
			process(con, def, true);
		}
	}

	private void process(Concept con, Definition def, boolean isGci) {
		// Use Eclipse Collections collect instead of stream
		MutableList<ElkClass> sups = def.getSuperConcepts()
				.collect(sup -> ontology.getElkClass(getIri(sup))).toList();
		MutableList<ElkObjectSomeValuesFrom> roles = def.getUngroupedRoles()
				.collect(this::process).toList();
		MutableList<ElkClassExpression> props = def.getUngroupedConcreteRoles()
				.collect(this::process).toList();
		MutableList<ElkObjectSomeValuesFrom> groups = def.getRoleGroups()
				.collect(this::process).toList();
		
		MutableList<ElkClassExpression> exprs = Lists.mutable.empty();
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

	protected ElkObjectSomeValuesFrom process(Role role) {
		return ontology.getElkObjectSomeValuesFrom(getIri(role.getRoleType()), getIri(role.getConcept()));
	}

	private ElkObjectSomeValuesFrom process(RoleGroup rg) {
		// Use Eclipse Collections collect instead of stream
		MutableList<ElkObjectSomeValuesFrom> roles = rg.getRoles().collect(this::process).toList();
		MutableList<ElkClassExpression> props = rg.getConcreteRoles().collect(this::process).toList();
		
		MutableList<ElkClassExpression> exprs = Lists.mutable.empty();
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

	protected ElkClassExpression process(ConcreteRole concreteRole) {
		ElkObject.Factory f = ontology.getObjectFactory();
		ElkDatatype datatype = switch (concreteRole.getValueType()) {
		case Boolean -> f.getXsdBoolean();
		case Decimal -> f.getXsdDecimal();
		case Double -> f.getXsdDouble();
		case Float -> f.getXsdFloat();
		case Integer -> f.getXsdInteger();
		case Long -> f.getXsdLong();
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
		Set<ElkObjectProperty> superProps = getSuperObjectProperties(rt, direct);
		Set<RoleType> result = new HashSet<>(superProps.size());
		for (ElkObjectProperty prop : superProps) {
			result.add(getRoleType(prop));
		}
		return result;
	}

	public MutableLongSet getSuperRoleTypes(long id, boolean direct) {
		RoleType rt = snomedOntology.getRoleType(id);
		if (rt == null)
			return null;
		Set<RoleType> superRoleTypes = getSuperRoleTypes(rt, direct);
		MutableLongSet result = LongSets.mutable.withInitialCapacity(superRoleTypes.size());
		for (RoleType roleType : superRoleTypes) {
			result.add(roleType.getId());
		}
		return result;
	}

	public MutableLongObjectMap<MutableLongSet> getSuperRoleTypes(boolean direct) {
		MutableLongObjectMap<MutableLongSet> superRoleTypes = LongObjectMaps.mutable.empty();
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
		Set<ElkClass> superClasses = getSuperClasses(con, direct);
		Set<Concept> result = new HashSet<>(superClasses.size());
		for (ElkClass clazz : superClasses) {
			result.add(getConcept(clazz));
		}
		return result;
	}

	public MutableLongSet getSuperConcepts(long id) {
		return getSuperConcepts(id, true);
	}

	public MutableLongSet getSuperConcepts(long id, boolean direct) {
		Concept con = snomedOntology.getConcept(id);
		if (con == null)
			return null;
		Set<Concept> superConcepts = getSuperConcepts(con, direct);
		MutableLongSet result = LongSets.mutable.withInitialCapacity(superConcepts.size());
		for (Concept concept : superConcepts) {
			result.add(concept.getId());
		}
		return result;
	}

	public MutableLongObjectMap<MutableLongSet> getSuperConcepts() {
		MutableLongObjectMap<MutableLongSet> superConcepts = LongObjectMaps.mutable.empty();
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
		Set<ElkClass> subClasses = getSubClasses(con, direct);
		Set<Concept> result = new HashSet<>(subClasses.size());
		for (ElkClass clazz : subClasses) {
			result.add(getConcept(clazz));
		}
		return result;
	}

	public MutableLongSet getSubConcepts(long id) {
		return getSubConcepts(id, true);
	}

	public MutableLongSet getSubConcepts(long id, boolean direct) {
		Concept con = snomedOntology.getConcept(id);
		if (con == null)
			return null;
		Set<Concept> subConcepts = getSubConcepts(con, direct);
		MutableLongSet result = LongSets.mutable.withInitialCapacity(subConcepts.size());
		for (Concept concept : subConcepts) {
			result.add(concept.getId());
		}
		return result;
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
		Set<ElkClass> equivalentClasses = getEquivalentClasses(con);
		Set<Concept> result = new HashSet<>(equivalentClasses.size());
		for (ElkClass clazz : equivalentClasses) {
			result.add(getConcept(clazz));
		}
		return result;
	}

	public MutableLongSet getEquivalentConcepts(long id) {
		Concept con = snomedOntology.getConcept(id);
		if (con == null)
			return null;
		Set<Concept> equivalentConcepts = getEquivalentConcepts(con);
		MutableLongSet result = LongSets.mutable.withInitialCapacity(equivalentConcepts.size());
		for (Concept concept : equivalentConcepts) {
			result.add(concept.getId());
		}
		return result;
	}

}
