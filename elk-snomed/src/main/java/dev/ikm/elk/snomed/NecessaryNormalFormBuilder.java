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
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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

	private List<Concept> concepts = new ArrayList<>();

	private SnomedIsa isa;

	private HashMap<RoleType, Set<RoleType>> superRolesTypes = new HashMap<>();

	private HashMap<Concept, Definition> necessaryNormalForm = new HashMap<>();

	private NNFSubsumption nnfSubsumption;

	public List<Concept> getConcepts() {
		return concepts;
	}

	public SnomedIsa getIsa() {
		return isa;
	}

	public HashMap<RoleType, Set<RoleType>> getSuperRolesTypes() {
		return superRolesTypes;
	}

	public HashMap<Concept, Definition> getNecessaryNormalForm() {
		return necessaryNormalForm;
	}

	public Definition getNecessaryNormalForm(Concept con) {
		return necessaryNormalForm.get(con);
	}

	public Definition getNecessaryNormalForm(long id) {
		Concept con = snomedOntology.getConcept(id);
		return necessaryNormalForm.get(con);
	}

	private NecessaryNormalFormBuilder(SnomedOntology snomedOntology) {
		super();
		this.snomedOntology = snomedOntology;

	}

	public static NecessaryNormalFormBuilder createNecessaryNormalFormBuilder(SnomedOntology snomedOntology,
			HashMap<Long, Set<Long>> superConcepts, HashMap<Long, Set<Long>> superRoleTypes) {
		NecessaryNormalFormBuilder nnfb = new NecessaryNormalFormBuilder(snomedOntology);
		nnfb.initConcepts(superConcepts);
		nnfb.initRoles(superRoleTypes);
		nnfb.nnfSubsumption = new NNFSubsumption(nnfb.isa, nnfb.superRolesTypes, nnfb.necessaryNormalForm);
		return nnfb;
	}

	private void initConcepts(HashMap<Long, Set<Long>> superConcepts) {
		isa = SnomedIsa.init(superConcepts);
		HashMap<Long, Set<Long>> dependentOnConcepts = new HashMap<>();
		for (Concept concept : snomedOntology.getConcepts()) {
			dependentOnConcepts.put(concept.getId(), getDependentOnConcepts(concept));
		}
		{
			SnomedIsa deps = SnomedIsa.init(dependentOnConcepts);
			deps.getOrderedConcepts().stream()
					.map(id -> snomedOntology.getConcept(id))
					.forEach(con -> concepts.add(con));
		}
		LOG.info("Concepts: " + concepts.size());
	}

	private final boolean log_roles = false;

	private void initRoles(HashMap<Long, Set<Long>> superRoles) {
		for (RoleType rt : snomedOntology.getRoleTypes()) {
			superRolesTypes.put(rt, new HashSet<>());
			superRolesTypes.get(rt).add(rt);
			superRoles.get(rt.getId()).stream().map(sup_id -> snomedOntology.getRoleType(sup_id))
					.forEach(sup_rt -> superRolesTypes.get(rt).add(sup_rt));
		}
		if (log_roles) {
			for (Entry<RoleType, Set<RoleType>> es : superRolesTypes.entrySet()) {
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

	private HashSet<Long> getDependentOnConcepts(Concept concept) {
		HashSet<Long> deps = new HashSet<>();
		long id = concept.getId();
		deps.addAll(isa.getParents(id));
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
		for (Concept concept : concepts) {
			if (++cnt % 50000 == 0)
				LOG.info("Generate: " + cnt);
			Definition def = generateNNF(concept, false);
			if (concept_comparer != null)
				concept_comparer.compare(concept, def);
		}
		LOG.info("Generate: " + cnt);
		if (concept_comparer != null)
			concept_comparer.logErrors();
	}

	public Definition generateNNF(Concept con, boolean useDefining) {
		Definition def = new Definition();
		if (con.getDefinitions().stream().map(Definition::getDefinitionType)
				.anyMatch(dt -> dt.equals(DefinitionType.EquivalentConcept))) {
			def.setDefinitionType(DefinitionType.EquivalentConcept);
		} else {
			def.setDefinitionType(DefinitionType.SubConcept);
		}
		List<Concept> sups;
		if (useDefining) {
			sups = con.getDefinitions().stream().flatMap(x -> x.getSuperConcepts().stream()).distinct().toList();
		} else {
			sups = isa.getParents(con.getId()).stream().map(x -> snomedOntology.getConcept(x)).distinct().toList();
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

//	private HashSet<Role> expandChain(RoleType role_type, Concept filler) {
//		HashSet<Role> svfs = new HashSet<>();
//		svfs.add(new Role(role_type, filler));
//		while (true) {
//			ArrayList<Role> svfs_l = new ArrayList<>(svfs);
//			for (Role svf : svfs_l) {
//				List<Role> chain_exps = expandChain1(svf);
//				svfs.addAll(chain_exps);
//				List<Role> sup_exps = expandSuperRoleTypes(svf);
//				svfs.addAll(sup_exps);
//			}
//			if (svfs.size() == svfs_l.size())
//				break;
//		}
//		return svfs;
//	}
//
//	private List<Role> expandChain1(Role svf) {
//		ArrayList<RoleType> chained_rts = new ArrayList<>();
//		if (svf.getRoleType().getChained() != null) {
//			chained_rts.add(svf.getRoleType().getChained());
//		}
//		if (svf.getRoleType().isTransitive())
//			chained_rts.add(svf.getRoleType());
//		List<Concept> chained_cons = necessaryNormalForm.get(svf.getConcept()).getUngroupedRoles().stream()
//				.filter(x -> chained_rts.contains(x.getRoleType())).map(x -> x.getConcept()).distinct().toList();
//		return chained_cons.stream().map(x -> new Role(svf.getRoleType(), x)).toList();
//	}
//
//	private List<Role> expandSuperRoleTypes(Role svf) {
//		return superRolesTypes.get(svf.getRoleType()).stream().filter(x -> !x.equals(svf.getRoleType()))
//				.map(x -> new Role(x, svf.getConcept())).toList();
//	}
//
//	/*
//	 * Return true if con1 is subsumed by con2
//	 */
//	public boolean isSubsumedBy(SnomedIsa definingIsa, SnomedOntology fullOntology, Concept con1, Concept con2) {
//		if (con1.equals(con2))
//			throw new RuntimeException(con1 + " " + con2);
//		Definition def1 = getNecessaryNormalForm(con1);
//		Definition def2 = getNecessaryNormalForm(con2);
////		LOG.info("s1:" + def1.getSuperConcepts());
////		LOG.info("s2:" + def2.getSuperConcepts());
//		HashSet<Long> ancestors2 = definingIsa.getAncestors(con2.getId());
//		ancestors2.add(con2.getId());
//		ancestors2.removeIf(anc2 -> anc2 != SnomedIds.root && fullOntology.getConcept(anc2).getDefinitions().getFirst()
//				.getDefinitionType() == DefinitionType.EquivalentConcept);
//		if (!ancestors2.stream().allMatch(anc2 -> definingIsa.hasAncestor(con1.getId(), anc2)))
//			return false;
////		LOG.info("r1:" + def1.getUngroupedRoles());
////		LOG.info("r2:" + def2.getUngroupedRoles());
//		if (!def2.getUngroupedRoles().stream().allMatch(
//				role2 -> def1.getUngroupedRoles().stream().anyMatch(role1 -> isSubClassOfEntailed(role1, role2))))
//			return false;
////		LOG.info("rg1:" + def1.getRoleGroups());
////		LOG.info("rg2:" + def2.getRoleGroups());
//		if (!def2.getRoleGroups().stream()
//				.allMatch(rg2 -> def1.getRoleGroups().stream().anyMatch(rg1 -> isSubsumedBy(rg1, rg2))))
//			return false;
//		return true;
//	}
//
//	private boolean isSubsumedBy(Concept con1, Concept con2) {
//		if (con1.equals(con2))
//			return true;
//		return isa.hasAncestor(con1.getId(), con2.getId());
//	}
//
//	private boolean isSubsumedBy(Role role1, Role role2) {
//		return superRolesTypes.get(role1.getRoleType()).contains(role2.getRoleType())
//				&& isSubsumedBy(role1.getConcept(), role2.getConcept());
//	}
//
//	private boolean isSubsumedBy(RoleGroup rg1, RoleGroup rg2) {
//		return rg2.getRoles().stream()
//				.allMatch(role2 -> rg1.getRoles().stream().anyMatch(role1 -> isSubClassOfEntailed(role1, role2)));
//	}
//
//	private boolean isSubClassOfEntailed(Role role1, Role role2) {
//		if (superRolesTypes.get(role1.getRoleType()).contains(role2.getRoleType())) {
//			{
//				Concept con1 = role1.getConcept();
//				Concept con2 = role2.getConcept();
//				if (isSubsumedBy(con1, con2))
//					return true;
//			}
//			HashSet<Role> chain1 = expandChain(role1.getRoleType(), role1.getConcept());
//			HashSet<Role> chain2 = expandChain(role2.getRoleType(), role2.getConcept());
//			boolean isSubsumedBy = chain2.stream()
//					.allMatch(svf2 -> chain1.stream().anyMatch(svf1 -> isSubsumedBy(svf1, svf2)));
//			return isSubsumedBy;
//		}
//		return false;
//	}

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
				if (role1.equals(role2))
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
