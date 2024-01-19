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
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.semanticweb.elk.reasoner.Reasoner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
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
//		snomedOntology = new OwlTransformer().transform(snomedOwlOntology);
		initConcepts();
		initRoles();
//		for (OWLAxiom ax : snomedOwlOntology.getOntology().getAxioms()) {
//			switch (ax.getAxiomType().getName()) {
//			case "SubClassOf" -> {
//			}
//			case "EquivalentClasses" -> {
//			}
//			case "SubObjectPropertyOf" -> {
//			}
//			case "SubPropertyChainOf" -> {
//			}
//			case "TransitiveObjectProperty" -> {
//			}
//			case "ReflexiveObjectProperty" -> {
//			}
//			default -> throw new UnsupportedOperationException("Unexpected: " + ax + " " + ax.getAxiomType());
//			}
//		}
	}

	private void initConcepts() {
//		HashMap<Long, Set<Long>> superConcepts = new HashMap<>();
		HashMap<Long, Set<Long>> dependentOnConcepts = new HashMap<>();
		for (Concept concept : snomedOntology.getConcepts()) {
			long id = concept.getId();
//			superConcepts.put(id, snomedOwlOntology.getSuperClasses(id));
			dependentOnConcepts.put(id, getDependentOnConcepts(concept));
		}
		isa = SnomedIsa.init(superConcepts);
		sortConcepts(dependentOnConcepts);
		LOG.info("Concepts: " + concepts.size());
	}

	private void sortConcepts(HashMap<Long, Set<Long>> dependentOnConcepts) {
		SnomedIsa deps = SnomedIsa.init(dependentOnConcepts);
		deps.getConcepts().stream().map(id -> snomedOntology.getConcept(id)).forEach(clazz -> concepts.add(clazz));
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
		HashSet<Long> dependentOnConcepts = new HashSet<>();
		long id = concept.getId();
		dependentOnConcepts.addAll(superConcepts.get(id));
		for (Definition def : concept.getDefinitions()) {
			List<RoleGroup> rgs = new ArrayList<>(def.getRoleGroups());
			for (Role role : def.getUngroupedRoles()) {
				dependentOnConcepts.add(role.getConcept().getId());
			}
			for (RoleGroup rg : rgs) {
				for (Role role : rg.getRoles()) {
					dependentOnConcepts.add(role.getConcept().getId());
				}
			}
		}
		return dependentOnConcepts;
	}

	public void generate() {
		generate(null);
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

	public void generate(SnomedRoles roles) {
		Reasoner.processingNecessaryNormalForm = true;
		int cnt = 0;
		for (Concept concept : concepts) {
			if (++cnt % 50000 == 0)
				LOG.info("Generate: " + cnt);
			necessaryNormalForm.put(concept, getNNF(concept));
			if (roles != null) {
				compare(concept, roles.getRoles(concept.getId()), necessaryNormalForm.get(concept));
			}
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

	private Definition getNNF(Concept con) {
		Definition def = new Definition();
		if (con.getDefinitions().stream().map(Definition::getDefinitionType)
				.anyMatch(dt -> dt.equals(DefinitionType.EquivalentConcept))) {
			def.setDefinitionType(DefinitionType.EquivalentConcept);
		} else {
			def.setDefinitionType(DefinitionType.SubConcept);
		}
		List<Concept> sups = superConcepts.get(con.getId()).stream().map(x -> snomedOntology.getConcept(x)).distinct()
				.collect(Collectors.toCollection(ArrayList::new));
		sups.forEach(sup -> def.addSuperConcept(sup));
		for (Concept sup : sups) {
			for (Role role : necessaryNormalForm.get(sup).getUngroupedRoles()) {
				def.addUngroupedRole(role);
			}
			for (RoleGroup rg : necessaryNormalForm.get(sup).getRoleGroups()) {
				def.addRoleGroup(rg);
			}
		}
		con.getDefinitions().forEach(con_def -> {
			con_def.getUngroupedRoles().forEach(role -> def.addUngroupedRole(role));
			con_def.getRoleGroups().forEach(rg -> {
				simplify(rg.getRoles());
				def.addRoleGroup(rg);
			});
		});
		simplify(def);
		return def;
	}

	private void simplify(Definition def) {
		simplify(def.getUngroupedRoles());
		simplifyGroup(def.getRoleGroups());
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

	private boolean isSubsumedBy(Concept con1, Concept con2) {
		if (con1.equals(con2))
			return true;
		return isa.hasAncestor(con1.getId(), con2.getId());
	}

	private boolean isSubsumedBy(Role svf1, Role svf2) {
		return superRolesTypes.get(svf1.getRoleType()).contains(svf2.getRoleType())
				&& isSubsumedBy(svf1.getConcept(), svf2.getConcept());
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

	private void simplify(Set<Role> roles) {
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

	private void simplifyGroup(Set<RoleGroup> rgs) {
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

	private void compare(Concept concept, Set<SnomedRoles.Role> roles, Definition definition) {
		if (roles == null)
			roles = Set.of();
		List<SnomedRoles.Role> sno_roles_ungrouped = roles.stream().filter(x -> x.relationshipGroup == 0).toList();
		Collection<List<SnomedRoles.Role>> sno_roles_grouped = roles.stream().filter(x -> x.relationshipGroup != 0)
				.collect(Collectors.groupingBy(x -> x.relationshipGroup)).values();
		boolean mis_match = false;
		List<SnomedRoles.Role> mis_match_sno_roles_grouped = new ArrayList<>();
		List<RoleGroup> mis_match_nnf_roles_grouped = new ArrayList<>();
		boolean inc;
		inc = false;
		for (SnomedRoles.Role sno_role : sno_roles_ungrouped) {
			boolean match = definition.getUngroupedRoles().stream().anyMatch(nnf_role -> compare(sno_role, nnf_role));
			if (!match) {
				LOG.error("No NNF roleU for " + concept + " " + sno_role);
				mis_match = true;
				inc = true;
			}
		}
		if (inc)
			mis_match_sno_roles_ungrouped_cnt++;
		inc = false;
		for (Role nnf_role : definition.getUngroupedRoles()) {
			boolean match = sno_roles_ungrouped.stream().anyMatch(sno_role -> compare(sno_role, nnf_role));
			if (!match) {
				LOG.error("No SNOMED roleU for " + concept + " " + nnf_role);
				mis_match = true;
				inc = true;
			}
		}
		if (inc)
			mis_match_nnf_roles_ungrouped_cnt++;
		inc = false;
		for (List<SnomedRoles.Role> sno_role : sno_roles_grouped) {
			boolean match = definition.getRoleGroups().stream()
					.anyMatch(nnf_role -> compare(sno_role, nnf_role.getRoles()));
			if (!match) {
				LOG.error("No NNF roleG for " + concept + " " + sno_role);
				mis_match = true;
				mis_match_sno_roles_grouped.addAll(sno_role);
				inc = true;
			}
		}
		if (inc)
			mis_match_sno_roles_grouped_cnt++;
		inc = false;
		for (RoleGroup nnf_role : definition.getRoleGroups()) {
			boolean match = sno_roles_grouped.stream().anyMatch(sno_role -> compare(sno_role, nnf_role.getRoles()));
			if (!match) {
				LOG.error("No SNOMED roleG for " + concept + " " + nnf_role);
				mis_match = true;
				mis_match_nnf_roles_grouped.add(nnf_role);
				inc = true;
			}
		}
		if (inc)
			mis_match_nnf_roles_grouped_cnt++;
		inc = false;
		if (mis_match) {
			mis_match_cnt++;
			LOG.info("Roles:");
			roles.stream()
					.sorted(Comparator.comparingLong((SnomedRoles.Role x) -> x.relationshipGroup)
							.thenComparingLong((SnomedRoles.Role x) -> x.typeId)
							.thenComparingLong((SnomedRoles.Role x) -> x.destinationId))
					.forEach(x -> LOG.info("\t" + x));
			LOG.info("NNF rolesU:");
			definition.getUngroupedRoles().forEach(x -> LOG.info("\t" + x));
			LOG.info("NNF rolesG:");
			definition.getRoleGroups().forEach(x -> {
				LOG.info("Group:");
				x.getRoles().forEach(y -> LOG.info("\t" + y));
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

	private boolean compare(List<SnomedRoles.Role> sno_roles, Set<Role> nnf_roles) {
		return sno_roles.stream()
				.allMatch(sno_role -> nnf_roles.stream().anyMatch(nnf_role -> compare(sno_role, nnf_role)))
				&& nnf_roles.stream()
						.allMatch(nnf_role -> sno_roles.stream().anyMatch(sno_role -> compare(sno_role, nnf_role)));
	}

	private boolean compare(SnomedRoles.Role sno_role, Role nnf_role) {
		return sno_role.typeId == nnf_role.getRoleType().getId()
				&& sno_role.destinationId == nnf_role.getConcept().getId();
	}

}
