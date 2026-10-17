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
import dev.ikm.elk.snomed.model.NestedRole;
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
		SnomedOntologyReasoner sor = createUninitialized(snomedOntology);
		sor.computeInferences();
		return sor;
	}

	/**
	 * Builds a reasoner over the ontology without classifying it.
	 *
	 * <p>{@link #create(SnomedOntology)} does both, and returns only once the classification has
	 * finished — which leaves a caller no handle on the reasoner while the slow part runs, and so
	 * no way to {@link #interrupt()} it. Taking the instance first and calling
	 * {@link #computeInferences()} separately is what makes a long classification cancellable.
	 *
	 * @param snomedOntology the ontology to reason over
	 * @return a reasoner that has not yet classified
	 */
	public static SnomedOntologyReasoner createUninitialized(SnomedOntology snomedOntology) {
		SnomedOntologyReasoner sor = new SnomedOntologyReasoner();
		sor.init(snomedOntology);
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
		processReflexiveChains();
		for (ConcreteRoleType dt : this.snomedOntology.getConcreteRoleTypes()) {
			process(dt);
		}
		for (Concept con : this.snomedOntology.getConcepts()) {
			process(con);
		}
	}

	/**
	 * Classifies the ontology. Slow — minutes on a full dataset.
	 *
	 * <p>Public so a caller that obtained the reasoner from
	 * {@link #createUninitialized(SnomedOntology)} can start the classification itself, holding a
	 * reference throughout and able to {@link #interrupt()} it.
	 */
	public void computeInferences() {
		reasoner = ElkReasoner.createReasoner(ontology, ontology.getObjectFactory());
		reasoner.flush();
		try {
			reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY, InferenceType.OBJECT_PROPERTY_HIERARCHY);
			// TODO ElkException
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Asks the underlying ELK reasoner to stop as soon as it can.
	 *
	 * <p>ELK checks for interruption between and within its stages, so an in-flight
	 * classification unwinds rather than running to completion. Mirrors what
	 * {@code ElkReasoner.interrupt()} does for the OWL API wrapper.
	 *
	 * <p>Safe to call at any time and from any thread. Before {@link #computeInferences()} has
	 * created the underlying reasoner there is nothing to interrupt and this does nothing, so a
	 * caller racing a cancel against the start of a classification need not guard against it.
	 *
	 * <p>An interrupted reasoner is not reusable for further inference; discard it and build
	 * another.
	 */
	public void interrupt() {
		ElkReasoner current = reasoner;
		if (current == null) {
			return;
		}
		current.getInternalReasoner().interrupt();
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
		for (List<RoleType> chain : rt.getChains()) {
			ElkAxiom axiom = ontology.getElkSubObjectPropertyChainOfAxiom(
					chain.stream().map(this::getIri).toList(), iri);
			ontology.addAxiom(axiom);
			LOG.info("Chain: " + axiom);
		}
		if (rt.isReflexive()) {
			ElkReflexiveObjectPropertyAxiom axiom = ontology.getElkReflexiveObjectPropertyAxiom(iri);
			ontology.addAxiom(axiom);
			LOG.info("Reflexive: " + axiom);
		}
	}

	/**
	 * States what reflexivity entails for the role hierarchy, which ELK does not derive: it reports
	 * object property classification incomplete when reflexive roles and property chains occur
	 * together (its {@code ObjectPropertyTaxonomyIncompleteness}). Each axiom added is entailed, so
	 * classification stays sound, and the role hierarchy becomes complete for them.
	 * <ul>
	 * <li>Reflexivity is closed: a role is reflexive when it is stated so, when a sub-role of it is
	 * reflexive, or when a chain of reflexive roles implies it.</li>
	 * <li>Each chain implying a role also implies it with any of its reflexive links left out, since
	 * a reflexive role holds between every individual and itself: {@code R ∘ S ⊑ H} with {@code S}
	 * reflexive gives {@code R ⊑ H}. A sequence of two or more is a chain; a single role is a
	 * sub-role.</li>
	 * </ul>
	 */
	private void processReflexiveChains() {
		// Every chain, the right identity SNOMED CT states as R ∘ S ⊑ R among them.
		MutableList<RoleType> implied = Lists.mutable.empty();
		MutableList<List<RoleType>> chains = Lists.mutable.empty();
		for (RoleType rt : snomedOntology.getRoleTypes()) {
			if (rt.getChained() != null) {
				implied.add(rt);
				chains.add(List.of(rt, rt.getChained()));
			}
			for (List<RoleType> chain : rt.getChains()) {
				implied.add(rt);
				chains.add(chain);
			}
		}
		Set<RoleType> reflexive = new HashSet<>();
		for (RoleType rt : snomedOntology.getRoleTypes()) {
			if (rt.isReflexive())
				reflexive.add(rt);
		}
		if (reflexive.isEmpty() || chains.isEmpty())
			return;
		boolean changed = true;
		while (changed) {
			changed = false;
			for (RoleType rt : snomedOntology.getRoleTypes()) {
				if (reflexive.contains(rt)) {
					for (RoleType sup : rt.getSuperRoleTypes())
						changed |= reflexive.add(sup);
				}
			}
			for (int i = 0; i < chains.size(); i++) {
				if (reflexive.containsAll(chains.get(i)))
					changed |= reflexive.add(implied.get(i));
			}
		}
		for (RoleType rt : reflexive) {
			if (!rt.isReflexive())
				ontology.addAxiom(ontology.getElkReflexiveObjectPropertyAxiom(getIri(rt)));
		}
		for (int i = 0; i < chains.size(); i++) {
			RoleType sup = implied.get(i);
			for (List<RoleType> shorter : withoutReflexiveLinks(chains.get(i), reflexive)) {
				ElkAxiom axiom;
				if (shorter.size() == 1) {
					if (shorter.getFirst().equals(sup))
						continue;
					axiom = ontology.getElkSubObjectPropertyOfAxiom(getIri(shorter.getFirst()), getIri(sup));
				} else {
					axiom = ontology.getElkSubObjectPropertyChainOfAxiom(shorter.stream().map(this::getIri).toList(),
							getIri(sup));
				}
				ontology.addAxiom(axiom);
				LOG.info("Entailed by reflexivity: " + axiom);
			}
		}
	}

	/**
	 * The sequences a chain leaves when some of its reflexive links are left out: every one but the
	 * chain itself and the empty sequence.
	 */
	private static List<List<RoleType>> withoutReflexiveLinks(List<RoleType> chain, Set<RoleType> reflexive) {
		List<Integer> reflexiveLinks = new ArrayList<>();
		for (int i = 0; i < chain.size(); i++) {
			if (reflexive.contains(chain.get(i)))
				reflexiveLinks.add(i);
		}
		List<List<RoleType>> sequences = new ArrayList<>();
		// Each non-empty subset of the reflexive links, left out.
		for (int mask = 1; mask < (1 << reflexiveLinks.size()); mask++) {
			Set<Integer> leftOut = new HashSet<>();
			for (int bit = 0; bit < reflexiveLinks.size(); bit++) {
				if ((mask & (1 << bit)) != 0)
					leftOut.add(reflexiveLinks.get(bit));
			}
			List<RoleType> sequence = new ArrayList<>();
			for (int i = 0; i < chain.size(); i++) {
				if (!leftOut.contains(i))
					sequence.add(chain.get(i));
			}
			if (!sequence.isEmpty())
				sequences.add(sequence);
		}
		return sequences;
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
		ElkClassExpression expr = expression(def);

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

	/** A definition's content as one class expression: its conjuncts, or the single one. */
	private ElkClassExpression expression(Definition def) {
		// Use Eclipse Collections collect instead of stream
		MutableList<ElkClass> sups = def.getSuperConcepts()
				.collect(sup -> ontology.getElkClass(getIri(sup))).toList();
		MutableList<ElkObjectSomeValuesFrom> roles = def.getUngroupedRoles()
				.collect(this::process).toList();
		MutableList<ElkObjectSomeValuesFrom> nested = def.getNestedRoles()
				.collect(this::process).toList();
		MutableList<ElkClassExpression> props = def.getUngroupedConcreteRoles()
				.collect(this::process).toList();
		MutableList<ElkObjectSomeValuesFrom> groups = def.getRoleGroups()
				.collect(this::process).toList();
		
		MutableList<ElkClassExpression> exprs = Lists.mutable.empty();
		exprs.addAll(sups);
		exprs.addAll(roles);
		exprs.addAll(nested);
		exprs.addAll(props);
		exprs.addAll(groups);
		
		if (exprs.size() == 1) {
			return exprs.getFirst();
		}
		return ontology.getObjectFactory().getObjectIntersectionOf(exprs);
	}

	protected ElkObjectSomeValuesFrom process(NestedRole role) {
		return ontology.getElkObjectSomeValuesFrom(getIri(role.roleType()), expression(role.filler()));
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
