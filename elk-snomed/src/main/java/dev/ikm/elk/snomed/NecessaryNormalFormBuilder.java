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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.semanticweb.elk.reasoner.Reasoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.SnomedConcreteRoles.SnomedConcreteRole;
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

	private HashMap<Long, Set<Long>> superConcepts;

	private HashMap<Long, Set<Long>> superRoles;

	private SnomedIsa isa;

	private HashMap<RoleType, Set<RoleType>> superRolesTypes = new HashMap<>();

	private HashMap<Concept, Definition> necessaryNormalForm = new HashMap<>();

	public List<Concept> getConcepts() {
		return concepts;
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

	public NecessaryNormalFormBuilder(SnomedOntology snomedOntology, HashMap<Long, Set<Long>> superConcepts,
			HashMap<Long, Set<Long>> superObjectProperties) {
		super();
		this.snomedOntology = snomedOntology;
		this.superConcepts = superConcepts;
		this.superRoles = superObjectProperties;
	}

	public void init() {
		initConcepts();
		initRoles();
	}

	private void initConcepts() {
		HashMap<Long, Set<Long>> dependentOnConcepts = new HashMap<>();
		for (Concept concept : snomedOntology.getConcepts()) {
			long id = concept.getId();
			dependentOnConcepts.put(id, getDependentOnConcepts(concept));
		}
		isa = SnomedIsa.init(superConcepts);
		sortConcepts(dependentOnConcepts);
		LOG.info("Concepts: " + concepts.size());
	}

	private void sortConcepts(HashMap<Long, Set<Long>> dependentOnConcepts) {
		SnomedIsa deps = SnomedIsa.init(dependentOnConcepts);
		deps.getOrderedConcepts().stream().map(id -> snomedOntology.getConcept(id))
				.forEach(clazz -> concepts.add(clazz));
	}

	private final boolean log_roles = false;

	private void initRoles() {
		for (RoleType rt : snomedOntology.getRoleTypes()) {
			superRolesTypes.put(rt, new HashSet<>());
			superRolesTypes.get(rt).add(rt);
			superRoles.get(rt.getId()).stream().map(x -> snomedOntology.getRoleType(x))
					.forEach(x -> superRolesTypes.get(rt).add(x));
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
		deps.addAll(superConcepts.get(id));
		deps.addAll(snomedOntology.getDependentOnConcepts(id, false, false));
		return deps;
	}

	public void generate() {
		generate(null, null);
	}

	int mis_match_cnt = 0;
	int mis_match_sno_roles_ungrouped_cnt = 0;
	int mis_match_nnf_roles_ungrouped_cnt = 0;
	int mis_match_sno_roles_grouped_cnt = 0;
	int mis_match_nnf_roles_grouped_cnt = 0;
	int mis_match_grouping_issue_cnt = 0;

	public int getMisMatchCount() {
		return mis_match_cnt;
	}

	public void generate(SnomedRoles roles, SnomedConcreteRoles concrete_roles) {
		Reasoner.processingNecessaryNormalForm = true;
		int cnt = 0;
		for (Concept concept : concepts) {
			if (++cnt % 50000 == 0)
				LOG.info("Generate: " + cnt);
			generateNNF(concept, false);
			if (roles != null)
				compare(concept, roles, concrete_roles);
		}
		LOG.info("Generate: " + cnt);
		Reasoner.processingNecessaryNormalForm = false;
		if (roles != null) {
			LOG.info("Mis match: " + mis_match_cnt);
			LOG.info("Mis match ungrouped: " + mis_match_sno_roles_ungrouped_cnt + " SNOMED roles "
					+ mis_match_nnf_roles_ungrouped_cnt + " NNF roles");
			LOG.info("Mis match grouped: " + mis_match_sno_roles_grouped_cnt + " SNOMED roles "
					+ mis_match_nnf_roles_grouped_cnt + " NNF roles");
			LOG.info("Mis match grouping issue: " + mis_match_grouping_issue_cnt);
		}
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
			sups = superConcepts.get(con.getId()).stream().map(x -> snomedOntology.getConcept(x)).distinct().toList();
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

	private HashSet<Role> expandChain(RoleType role_type, Concept filler) {
		HashSet<Role> svfs = new HashSet<>();
		svfs.add(new Role(role_type, filler));
		while (true) {
			ArrayList<Role> svfs_l = new ArrayList<>(svfs);
			for (Role svf : svfs_l) {
				List<Role> chain_exps = expandChain1(svf);
				svfs.addAll(chain_exps);
				List<Role> sup_exps = expandSuperRoleTypes(svf);
				svfs.addAll(sup_exps);
			}
			if (svfs.size() == svfs_l.size())
				break;
		}
		return svfs;
	}

	private List<Role> expandChain1(Role svf) {
		ArrayList<RoleType> chained_rts = new ArrayList<>();
		if (svf.getRoleType().getChained() != null) {
			chained_rts.add(svf.getRoleType().getChained());
		}
		if (svf.getRoleType().isTransitive())
			chained_rts.add(svf.getRoleType());
		List<Concept> chained_cons = necessaryNormalForm.get(svf.getConcept()).getUngroupedRoles().stream()
				.filter(x -> chained_rts.contains(x.getRoleType())).map(x -> x.getConcept()).distinct().toList();
		return chained_cons.stream().map(x -> new Role(svf.getRoleType(), x)).toList();
	}

	private List<Role> expandSuperRoleTypes(Role svf) {
		return superRolesTypes.get(svf.getRoleType()).stream().filter(x -> !x.equals(svf.getRoleType()))
				.map(x -> new Role(x, svf.getConcept())).toList();
	}

	/*
	 * Return true if con1 is subsumed by con2
	 */
	public boolean isSubsumedBy(SnomedIsa definingIsa, SnomedOntology fullOntology, Concept con1, Concept con2) {
		if (con1.equals(con2))
			throw new RuntimeException(con1 + " " + con2);
		Definition def1 = getNecessaryNormalForm(con1);
		Definition def2 = getNecessaryNormalForm(con2);
//		LOG.info("s1:" + def1.getSuperConcepts());
//		LOG.info("s2:" + def2.getSuperConcepts());
		HashSet<Long> ancestors2 = definingIsa.getAncestors(con2.getId());
		ancestors2.add(con2.getId());
		ancestors2.removeIf(anc2 -> anc2 != SnomedIds.root && fullOntology.getConcept(anc2).getDefinitions()
				.getFirst().getDefinitionType() == DefinitionType.EquivalentConcept);
		if (!ancestors2.stream().allMatch(anc2 -> definingIsa.hasAncestor(con1.getId(), anc2)))
			return false;
//		LOG.info("r1:" + def1.getUngroupedRoles());
//		LOG.info("r2:" + def2.getUngroupedRoles());
		if (!def2.getUngroupedRoles().stream().allMatch(
				role2 -> def1.getUngroupedRoles().stream().anyMatch(role1 -> isSubClassOfEntailed(role1, role2))))
			return false;
//		LOG.info("rg1:" + def1.getRoleGroups());
//		LOG.info("rg2:" + def2.getRoleGroups());
		if (!def2.getRoleGroups().stream()
				.allMatch(rg2 -> def1.getRoleGroups().stream().anyMatch(rg1 -> isSubsumedBy(rg1, rg2))))
			return false;
		return true;
	}

	private boolean isSubsumedBy(Concept con1, Concept con2) {
		if (con1.equals(con2))
			return true;
		return isa.hasAncestor(con1.getId(), con2.getId());
	}

	private boolean isSubsumedBy(Role role1, Role role2) {
		return superRolesTypes.get(role1.getRoleType()).contains(role2.getRoleType())
				&& isSubsumedBy(role1.getConcept(), role2.getConcept());
	}

	private boolean isSubsumedBy(RoleGroup rg1, RoleGroup rg2) {
		return rg2.getRoles().stream()
				.allMatch(role2 -> rg1.getRoles().stream().anyMatch(role1 -> isSubClassOfEntailed(role1, role2)));
	}

	private boolean isSubClassOfEntailed(Role role1, Role role2) {
		if (superRolesTypes.get(role1.getRoleType()).contains(role2.getRoleType())) {
			{
				Concept con1 = role1.getConcept();
				Concept con2 = role2.getConcept();
				if (isSubsumedBy(con1, con2))
					return true;
			}
			HashSet<Role> chain1 = expandChain(role1.getRoleType(), role1.getConcept());
			HashSet<Role> chain2 = expandChain(role2.getRoleType(), role2.getConcept());
			boolean isSubsumedBy = chain2.stream()
					.allMatch(svf2 -> chain1.stream().anyMatch(svf1 -> isSubsumedBy(svf1, svf2)));
			return isSubsumedBy;
		}
		return false;
	}

	private void simplifyRoles(Set<Role> roles) {
		if (roles.isEmpty())
			return;
		ArrayList<Role> to_remove = new ArrayList<>();
		for (Role role1 : roles) {
			for (Role role2 : roles) {
				if (role1 == role2)
					continue;
				if (isSubClassOfEntailed(role1, role2))
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
				boolean isSubClassOf = rg2.getRoles().stream()
						.allMatch(r2 -> rg1.getRoles().stream().anyMatch(r1 -> isSubClassOfEntailed(r1, r2)));
				if (isSubClassOf)
					to_remove.add(rg2);
			}
		}
		rgs.removeAll(to_remove);
	}

	HashSet<Long> mis_match_cons = new HashSet<>();
	int root_mis_match_cnt = 0;

	private static class SnomedRoleGroup {
		List<SnomedRoles.SnomedRole> roles = new ArrayList<>();
		List<SnomedConcreteRoles.SnomedConcreteRole> numberProperties = new ArrayList<>();
	}

	private List<SnomedRoleGroup> getRoleGroups(long con, SnomedRoles roles, SnomedConcreteRoles concrete_roles) {
		HashMap<Long, SnomedRoleGroup> groups = new HashMap<>();
		for (SnomedRoles.SnomedRole role : roles.getGroupedRoles(con)) {
			groups.putIfAbsent(role.relationshipGroup, new SnomedRoleGroup());
			groups.get(role.relationshipGroup).roles.add(role);
		}
		for (SnomedConcreteRole role : concrete_roles.getGroupedConcreteRoles(con)) {
			groups.putIfAbsent(role.relationshipGroup, new SnomedRoleGroup());
			groups.get(role.relationshipGroup).numberProperties.add(role);
		}
		return new ArrayList<>(groups.values());
	}

	private boolean compareUngrouped(Concept concept, List<SnomedRoles.SnomedRole> sno_roles_ungrouped,
			Set<Role> nnf_roles_ungrouped) {
		boolean mis_match = false;
		for (SnomedRoles.SnomedRole sno_role : sno_roles_ungrouped) {
			boolean match = nnf_roles_ungrouped.stream().anyMatch(nnf_role -> compare(sno_role, nnf_role));
			if (!match) {
				LOG.error("No NNF roleU for " + concept + " " + sno_role);
				mis_match = true;
			}
		}
		return mis_match;
	}

	private boolean compareUngrouped(Concept concept, Set<Role> nnf_roles_ungrouped,
			List<SnomedRoles.SnomedRole> sno_roles_ungrouped) {
		boolean mis_match = false;
		for (Role nnf_role : nnf_roles_ungrouped) {
			boolean match = sno_roles_ungrouped.stream().anyMatch(sno_role -> compare(sno_role, nnf_role));
			if (!match) {
				LOG.error("No SNOMED roleU for " + concept + " " + nnf_role);
				mis_match = true;
			}
		}
		return mis_match;
	}

	private boolean compareUngroupedConcrete(Concept concept, List<SnomedConcreteRole> sno_roles_ungrouped,
			Set<ConcreteRole> nnf_roles_ungrouped) {
		boolean mis_match = false;
		for (SnomedConcreteRole sno_role : sno_roles_ungrouped) {
			boolean match = nnf_roles_ungrouped.stream().anyMatch(nnf_role -> compare(sno_role, nnf_role));
			if (!match) {
				LOG.error("No NNF roleU for " + concept + " " + sno_role);
				mis_match = true;
			}
		}
		return mis_match;
	}

	private boolean compareUngroupedConcrete(Concept concept, Set<ConcreteRole> nnf_roles_ungrouped,
			List<SnomedConcreteRole> sno_roles_ungrouped) {
		boolean mis_match = false;
		for (ConcreteRole nnf_role : nnf_roles_ungrouped) {
			boolean match = sno_roles_ungrouped.stream().anyMatch(sno_role -> compare(sno_role, nnf_role));
			if (!match) {
				LOG.error("No SNOMED roleU for " + concept + " " + nnf_role);
				mis_match = true;
			}
		}
		return mis_match;
	}

	private void compare(Concept concept, SnomedRoles roles, SnomedConcreteRoles concrete_roles) {
		Definition definition = necessaryNormalForm.get(concept);
		List<SnomedRoles.SnomedRole> sno_roles_ungrouped = roles.getUngroupedRoles(concept.getId());
		List<SnomedConcreteRole> sno_croles_ungrouped = concrete_roles.getUngroupedConcreteRoles(concept.getId());
		List<SnomedRoleGroup> sno_roles_grouped = getRoleGroups(concept.getId(), roles, concrete_roles);
		//
		Set<Role> nnf_roles_ungrouped = definition.getUngroupedRoles();
		Set<ConcreteRole> nnf_croles_ungrouped = definition.getUngroupedConcreteRoles();
		Set<RoleGroup> nnf_roles_grouped = definition.getRoleGroups();
		//
		boolean mis_match = false;
		List<SnomedRoleGroup> mis_match_sno_roles_grouped = new ArrayList<>();
		List<RoleGroup> mis_match_nnf_roles_grouped = new ArrayList<>();
		if (compareUngrouped(concept, sno_roles_ungrouped, nnf_roles_ungrouped)) {
			mis_match = true;
			mis_match_sno_roles_ungrouped_cnt++;
		}
		if (compareUngrouped(concept, nnf_roles_ungrouped, sno_roles_ungrouped)) {
			mis_match = true;
			mis_match_nnf_roles_ungrouped_cnt++;
		}
		if (compareUngroupedConcrete(concept, sno_croles_ungrouped, nnf_croles_ungrouped)) {
			mis_match = true;
//			mis_match_sno_roles_ungrouped_cnt++;
		}
		if (compareUngroupedConcrete(concept, nnf_croles_ungrouped, sno_croles_ungrouped)) {
			mis_match = true;
//			mis_match_nnf_roles_ungrouped_cnt++;
		}
		{
			boolean inc = false;
			for (SnomedRoleGroup sno_role : sno_roles_grouped) {
				boolean match = nnf_roles_grouped.stream()
						.anyMatch(nnf_role -> compare(sno_role.roles, nnf_role.getRoles())
								&& compareConcrete(sno_role.numberProperties, nnf_role.getConcreteRoles()));
				if (!match) {
					LOG.error("No NNF roleG for " + concept + " " + sno_role);
					mis_match = true;
					mis_match_sno_roles_grouped.add(sno_role);
					inc = true;
				}
			}
			if (inc)
				mis_match_sno_roles_grouped_cnt++;
		}
		{
			boolean inc = false;
			for (RoleGroup nnf_role : nnf_roles_grouped) {
				boolean match = sno_roles_grouped.stream()
						.anyMatch(sno_role -> compare(sno_role.roles, nnf_role.getRoles())
								&& compareConcrete(sno_role.numberProperties, nnf_role.getConcreteRoles()));
				if (!match) {
					LOG.error("No SNOMED roleG for " + concept + " " + nnf_role);
					mis_match = true;
					mis_match_nnf_roles_grouped.add(nnf_role);
					inc = true;
				}
			}
			if (inc)
				mis_match_nnf_roles_grouped_cnt++;
		}
		if (mis_match) {
			mis_match_cnt++;
			LOG.info("SNO rolesU:");
			sno_roles_ungrouped.stream()
					.sorted(Comparator.comparingLong((SnomedRoles.SnomedRole x) -> x.relationshipGroup)
							.thenComparingLong((SnomedRoles.SnomedRole x) -> x.typeId)
							.thenComparingLong((SnomedRoles.SnomedRole x) -> x.destinationId))
					.forEach(x -> LOG.info("\t" + x));
			LOG.info("SNO concreteRolesU:");
			sno_croles_ungrouped.stream().forEach(x -> LOG.info("\t" + x));
			LOG.info("SNO rolesG:");
			sno_roles_grouped.stream().forEach(rg -> {
				LOG.info("Group:");
				rg.roles.forEach(r -> LOG.info("\t" + r));
				rg.numberProperties.forEach(r -> LOG.info("\t" + r));
			});
			LOG.info("NNF rolesU:");
			definition.getUngroupedRoles().forEach(x -> LOG.info("\t" + x));
			LOG.info("NNF concreteRolesU:");
			definition.getUngroupedConcreteRoles().forEach(x -> LOG.info("\t" + x));
			LOG.info("NNF rolesG:");
			definition.getRoleGroups().forEach(rg -> {
				LOG.info("Group:");
				rg.getRoles().forEach(r -> LOG.info("\t" + r));
				rg.getConcreteRoles().forEach(y -> LOG.info("\t" + y));
			});
			if (!mis_match_cons.stream().anyMatch(x -> isa.hasAncestor(concept.getId(), x))) {
				LOG.info("Root mis-match: " + concept.getId());
				root_mis_match_cnt++;
			}
			mis_match_cons.add(concept.getId());
			LOG.info("Root mis-match cnt: " + root_mis_match_cnt);
//			if (mis_match_roles_grouped.size() > 0 && mis_match_props_grouped.size() > 0) {
//				if (compare(mis_match_roles_grouped, mis_match_props_grouped)) {
//					LOG.warn("Grouping issue");
//					mis_match_grouping_issue_cnt++;
//					grouping_issue_concepts.add(concept);
//				} else {
//					LOG.error("Not a grouping issue - compare");
//					boolean ancestor_grouping_issue = grouping_issue_concepts.stream().anyMatch(
//							x -> isa.hasAncestor(SnomedOwlOntology.getId(concept), SnomedOwlOntology.getId(x)));
//					if (ancestor_grouping_issue)
//						LOG.warn("Grouping issue with ancestor");
//				}
//			} else {
//				LOG.error("Not a grouping issue - size");
//			}
		}
	}

	private boolean compare(List<SnomedRoles.SnomedRole> sno_roles, Set<Role> nnf_roles) {
		return sno_roles.stream()
				.allMatch(sno_role -> nnf_roles.stream().anyMatch(nnf_role -> compare(sno_role, nnf_role)))
				&& nnf_roles.stream()
						.allMatch(nnf_role -> sno_roles.stream().anyMatch(sno_role -> compare(sno_role, nnf_role)));
	}

	private boolean compare(SnomedRoles.SnomedRole sno_role, Role nnf_role) {
		return sno_role.typeId == nnf_role.getRoleType().getId()
				&& sno_role.destinationId == nnf_role.getConcept().getId();
	}

	private boolean compareConcrete(List<SnomedConcreteRoles.SnomedConcreteRole> sno_roles,
			Set<ConcreteRole> nnf_roles) {
		return sno_roles.stream()
				.allMatch(sno_role -> nnf_roles.stream().anyMatch(nnf_role -> compare(sno_role, nnf_role)))
				&& nnf_roles.stream()
						.allMatch(nnf_role -> sno_roles.stream().anyMatch(sno_role -> compare(sno_role, nnf_role)));
	}

	private boolean compare(SnomedConcreteRole sno_role, ConcreteRole nnf_role) {
		BigDecimal sno_value = new BigDecimal(sno_role.value.replace("#", ""));
		BigDecimal nnf_value = new BigDecimal(nnf_role.getValue());
		return sno_role.typeId == nnf_role.getConcreteRoleType().getId() && sno_value.compareTo(nnf_value) == 0;
	}

}
