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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.primitive.LongObjectMaps;
import org.eclipse.collections.api.factory.primitive.LongSets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;

public class NecessaryNormalFormBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(NecessaryNormalFormBuilder.class);

	private SnomedOntology snomedOntology;

	// Use Eclipse Collections adaptive list
	private MutableList<Concept> concepts = Lists.mutable.empty();

	protected SnomedIsa isa;

	private long root;

	// Use Eclipse Collections maps - better performance for object keys
	protected MutableMap<RoleType, MutableSet<RoleType>> superRolesTypes = Maps.mutable.empty();
	protected MutableMap<Concept, Definition> necessaryNormalForm = Maps.mutable.empty();

	protected NNFSubsumption nnfSubsumption;

	public List<Concept> getConcepts() {
		return concepts;
	}

	public SnomedIsa getIsa() {
		return isa;
	}

	public MutableMap<RoleType, MutableSet<RoleType>> getSuperRolesTypes() {
		return superRolesTypes;
	}

	public MutableMap<Concept, Definition> getNecessaryNormalForm() {
		return necessaryNormalForm;
	}

	public Definition getNecessaryNormalForm(Concept con) {
		return necessaryNormalForm.get(con);
	}

	public Definition getNecessaryNormalForm(long id) {
		Concept con = snomedOntology.getConcept(id);
		return necessaryNormalForm.get(con);
	}

	@FunctionalInterface
	public interface ProgressUpdater {
		void update(int workDone, int max);
	}

	final ProgressUpdater progressUpdater;

	protected NecessaryNormalFormBuilder(SnomedOntology snomedOntology, long root, ProgressUpdater progressUpdater) {
		super();
		this.snomedOntology = snomedOntology;
		this.root = root;
		this.progressUpdater = progressUpdater;
	}

	public static NecessaryNormalFormBuilder create(SnomedOntology snomedOntology,
			MutableLongObjectMap<MutableLongSet> superConcepts,  // ← Primitive map!
			MutableLongObjectMap<MutableLongSet> superRoleTypes,  // ← Primitive map!
			ProgressUpdater progressUpdater) {
		return create(snomedOntology, superConcepts, superRoleTypes, SnomedIds.root, progressUpdater);
	}

	public static NecessaryNormalFormBuilder create(SnomedOntology snomedOntology,
			MutableLongObjectMap<MutableLongSet> superConcepts,  // ← Primitive map!
			MutableLongObjectMap<MutableLongSet> superRoleTypes,  // ← Primitive map!
			long root,
			ProgressUpdater progressUpdater) {
		NecessaryNormalFormBuilder nnfb = new NecessaryNormalFormBuilder(snomedOntology, root, progressUpdater);
		nnfb.initConcepts(superConcepts);
		nnfb.initRoles(superRoleTypes);
		nnfb.initSubsumption();
		return nnfb;
	}

	protected void initSubsumption() {
		nnfSubsumption = new NNFSubsumption(isa, superRolesTypes, necessaryNormalForm);
	}

	protected void initConcepts(MutableLongObjectMap<MutableLongSet> superConcepts) {
		isa = SnomedIsa.init(superConcepts, root);
		MutableLongObjectMap<MutableLongSet> dependentOnConcepts = LongObjectMaps.mutable.ofInitialCapacity(
				snomedOntology.getConcepts().size());
		
		for (Concept concept : snomedOntology.getConcepts()) {
			dependentOnConcepts.put(concept.getId(), getDependentOnConcepts(concept));
		}
		
		SnomedIsa deps = SnomedIsa.init(dependentOnConcepts, root);
		deps.getOrderedConcepts().forEach(id -> concepts.add(snomedOntology.getConcept(id)));
		
		LOG.info("Concepts: " + concepts.size());
	}

	private final boolean log_roles = false;

	protected void initRoles(MutableLongObjectMap<MutableLongSet> superRoles) {
		for (RoleType rt : snomedOntology.getRoleTypes()) {
			MutableSet<RoleType> superTypes = Sets.mutable.empty();
			superRolesTypes.put(rt, superTypes);
			superTypes.add(rt);
			
			// Use primitive forEach - no boxing!
			superRoles.get(rt.getId()).forEach(sup_id -> {
				RoleType sup_rt = snomedOntology.getRoleType(sup_id);
				superTypes.add(sup_rt);
			});
		}
		
		if (log_roles) {
			for (Entry<RoleType, MutableSet<RoleType>> es : superRolesTypes.entrySet()) {
				RoleType rt = es.getKey();
				LOG.info("RoleType: " + rt.getId() + " " + rt.isTransitive() + " " + rt.getChained());
				if (es.getValue().size() > 1) {
					LOG.info("Super RoleTypes: " + rt.getId());
					for (RoleType sup_rt : es.getValue()) {
						if (sup_rt != rt)
							LOG.info("\t" + sup_rt.getId());
					}
				}
			}
		}
	}

	private MutableLongSet getDependentOnConcepts(Concept concept) {
		MutableLongSet deps = LongSets.mutable.empty();  // ← Primitive set!
		long id = concept.getId();
		deps.addAll(isa.getParents(id));  // Now returns primitive set
		deps.addAll(snomedOntology.getDependentOnConcepts(id, false, false));
		return deps;
	}

	public void generate() {
		generate(null);
	}

	private ConceptComparer conceptComparer = null;

	public int getMisMatchCount() {
		return conceptComparer.getMisMatchCount();
	}

	public void generate(ConceptComparer concept_comparer) {
		this.conceptComparer = concept_comparer;
		int cnt = 0;
		final int size = concepts.size();
		for (Concept concept : concepts) {
			if (++cnt % 50000 == 0) {
				LOG.info("Generate: " + String.format("%,d", cnt));
				progressUpdater.update(cnt, size);
			}
			Definition def = generateNNF(concept, false);
			Concept nnf_con = new Concept(concept.getId());
			nnf_con.addDefinition(def);
			if (concept_comparer != null)
				concept_comparer.compare(nnf_con);
		}
		LOG.info("Generate: " + String.format("%,d", cnt));
		if (concept_comparer != null && concept_comparer.getMisMatchCount() != 0)
			LOG.error("Mis-match: " + concept_comparer.getMisMatchCount());
	}

	public Definition generateNNF(Concept con, boolean useDefining) {
		Definition def = new Definition();
		if (con.getDefinitions().stream().map(Definition::getDefinitionType)
				.anyMatch(dt -> dt.equals(DefinitionType.EquivalentConcept))) {
			def.setDefinitionType(DefinitionType.EquivalentConcept);
		} else {
			def.setDefinitionType(DefinitionType.SubConcept);
		}
		MutableSet<Concept> sups;
		if (useDefining) {
			sups = con.getDefinitions()
					.flatCollect(Definition::getSuperConcepts)
					.toSet();
		} else {
			sups = isa.getParents(con.getId())
					.collect(snomedOntology::getConcept)
					.toSet();
		}
		sups.forEach(sup -> def.addSuperConcept(sup));
		for (Concept sup : sups) {
			for (Role role : necessaryNormalForm.get(sup).getUngroupedRoles()) {
				def.addUngroupedRole(role);
			}
			for (ConcreteRole role : necessaryNormalForm.get(sup).getUngroupedConcreteRoles()) {
				def.addUngroupedConcreteRole(role);
			}
			for (RoleGroup rg : necessaryNormalForm.get(sup).getRoleGroups()) {
				def.addRoleGroup(rg);
			}
		}
		con.getDefinitions().forEach(con_def -> {
			con_def.getUngroupedRoles().forEach(role -> def.addUngroupedRole(role));
			con_def.getUngroupedConcreteRoles().forEach(role -> def.addUngroupedConcreteRole(role));
			con_def.getRoleGroups().forEach(rg -> {
				simplifyRoles(rg.getRoles());
				simplifyConcreteRoles(rg.getConcreteRoles());
				def.addRoleGroup(rg);
			});
		});
		simplify(def);
		necessaryNormalForm.put(con, def);
		return def;
	}

	private void simplify(Definition def) {
		simplifyRoles(def.getUngroupedRoles());
		simplifyConcreteRoles(def.getUngroupedConcreteRoles());
		simplifyGroups(def.getRoleGroups());
	}

	private void simplifyRoles(Set<Role> roles) {
		if (roles.isEmpty())
			return;
		ArrayList<Role> to_remove = new ArrayList<>();
		for (Role role1 : roles) {
			for (Role role2 : roles) {
				if (role1 == role2)
					continue;
				if (nnfSubsumption.isSubRoleOfEntailed(role1, role2))
					to_remove.add(role2);
			}
		}
		roles.removeAll(to_remove);
	}

	private void simplifyConcreteRoles(Set<ConcreteRole> roles) {
		if (roles.isEmpty())
			return;
		ArrayList<ConcreteRole> to_remove = new ArrayList<>();
		for (ConcreteRole role1 : roles) {
			for (ConcreteRole role2 : roles) {
				if (role1 == role2)
					continue;
				if (nnfSubsumption.isSubsumedBy(role1, role2))
					to_remove.add(role2);
			}
		}
		roles.removeAll(to_remove);
	}

	private void simplifyGroups(Set<RoleGroup> rgs) {
		if (rgs.isEmpty())
			return;
		ArrayList<RoleGroup> to_remove = new ArrayList<>();
		for (RoleGroup rg1 : rgs) {
			for (RoleGroup rg2 : rgs) {
				if (rg1 == rg2)
					continue;
				boolean isSubClassOf = rg2.getRoles().stream().allMatch(
						r2 -> rg1.getRoles().stream().anyMatch(r1 -> nnfSubsumption.isSubRoleOfEntailed(r1, r2)));
				if (isSubClassOf)
					to_remove.add(rg2);
			}
		}
		rgs.removeAll(to_remove);
	}

}
