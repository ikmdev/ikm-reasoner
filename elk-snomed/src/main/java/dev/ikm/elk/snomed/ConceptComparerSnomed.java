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

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.SnomedConcreteRoles.SnomedConcreteRole;
import dev.ikm.elk.snomed.SnomedRoleGroups.SnomedRoleGroup;
import dev.ikm.elk.snomed.SnomedRoles.SnomedRole;
import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;

@Deprecated
public class ConceptComparerSnomed {

	private static final Logger LOG = LoggerFactory.getLogger(ConceptComparerSnomed.class);

	private SnomedConcepts concepts;

	private SnomedRoles roles;

	private SnomedConcreteRoles concreteRoles;

	private HashSet<Long> mis_match_cons = new HashSet<>();

	private int mis_match_cnt = 0;
	private int mis_match_sno_roles_ungrouped_cnt = 0;
	private int mis_match_nnf_roles_ungrouped_cnt = 0;
	private int mis_match_sno_roles_grouped_cnt = 0;
	private int mis_match_nnf_roles_grouped_cnt = 0;
	private int mis_match_grouping_issue_cnt = 0;

	public ConceptComparerSnomed(SnomedConcepts concepts, SnomedRoles roles, SnomedConcreteRoles concreteRoles) {
		this.concepts = concepts;
		this.roles = roles;
		this.concreteRoles = concreteRoles;
	}

	public int getMisMatchCount() {
		return mis_match_cnt;
	}

	public void logErrors() {
		LOG.info("Mis match: " + mis_match_cnt);
		LOG.info("Mis match ungrouped: " + mis_match_sno_roles_ungrouped_cnt + " SNOMED roles "
				+ mis_match_nnf_roles_ungrouped_cnt + " NNF roles");
		LOG.info("Mis match grouped: " + mis_match_sno_roles_grouped_cnt + " SNOMED roles "
				+ mis_match_nnf_roles_grouped_cnt + " NNF roles");
		LOG.info("Mis match grouping issue: " + mis_match_grouping_issue_cnt);
	}

	private boolean compareUngrouped(Concept concept, List<SnomedRole> sno_roles_ungrouped,
			Set<Role> nnf_roles_ungrouped) {
		boolean mis_match = false;
		for (SnomedRole sno_role : sno_roles_ungrouped) {
			boolean match = nnf_roles_ungrouped.stream().anyMatch(nnf_role -> compare(sno_role, nnf_role));
			if (!match) {
				LOG.error("No NNF roleU for " + concept + " " + sno_role);
				mis_match = true;
			}
		}
		return mis_match;
	}

	private boolean compareUngrouped(Concept concept, Set<Role> nnf_roles_ungrouped,
			List<SnomedRole> sno_roles_ungrouped) {
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

	private boolean compareRoleGroups(Concept concept, List<SnomedRoleGroup> sno_roles_grouped,
			Set<RoleGroup> nnf_roles_grouped) {
		boolean mis_match = false;
		for (SnomedRoleGroup sno_role : sno_roles_grouped) {
			boolean match = nnf_roles_grouped.stream().anyMatch(nnf_role -> compare(sno_role.roles, nnf_role.getRoles())
					&& compareConcrete(sno_role.concreteRoles, nnf_role.getConcreteRoles()));
			if (!match) {
				LOG.error("No NNF roleG for " + concept + " " + sno_role);
				mis_match = true;
			}
		}
		return mis_match;
	}

	private boolean compareRoleGroups(Concept concept, Set<RoleGroup> nnf_roles_grouped,
			List<SnomedRoleGroup> sno_roles_grouped) {
		boolean mis_match = false;
		for (RoleGroup nnf_role : nnf_roles_grouped) {
			boolean match = sno_roles_grouped.stream().anyMatch(sno_role -> compare(sno_role.roles, nnf_role.getRoles())
					&& compareConcrete(sno_role.concreteRoles, nnf_role.getConcreteRoles()));
			if (!match) {
				LOG.error("No SNOMED roleG for " + concept + " " + nnf_role);
				mis_match = true;
			}
		}
		return mis_match;
	}

	public void compare(Concept concept, Definition definition) {
		List<SnomedRole> sno_roles_ungrouped = roles.getUngroupedRoles(concept.getId());
		List<SnomedConcreteRole> sno_croles_ungrouped = concreteRoles.getUngroupedConcreteRoles(concept.getId());
		List<SnomedRoleGroup> sno_roles_grouped = SnomedRoleGroups.getRoleGroups(concept.getId(), roles, concreteRoles);
		//
		Set<Role> nnf_roles_ungrouped = definition.getUngroupedRoles();
		Set<ConcreteRole> nnf_croles_ungrouped = definition.getUngroupedConcreteRoles();
		Set<RoleGroup> nnf_roles_grouped = definition.getRoleGroups();
		//
		boolean mis_match = false;
		{
			DefinitionType expect = switch (concepts.getDefinitionStatus(concept.getId())) {
			case NECESSARY -> DefinitionType.SubConcept;
			case SUFFICIENT -> DefinitionType.EquivalentConcept;
			};
			if (expect != definition.getDefinitionType()) {
				LOG.error("Definition type: " + concept + " " + expect + " " + definition.getDefinitionType());
				mis_match = true;
			}
		}
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
		if (compareRoleGroups(concept, sno_roles_grouped, nnf_roles_grouped)) {
			mis_match = true;
			mis_match_sno_roles_grouped_cnt++;
		}
		if (compareRoleGroups(concept, nnf_roles_grouped, sno_roles_grouped)) {
			mis_match = true;
			mis_match_nnf_roles_grouped_cnt++;
		}
		if (mis_match) {
			mis_match_cnt++;
			LOG.info("SNO rolesU:");
			sno_roles_ungrouped.stream().sorted(Comparator.comparingLong((SnomedRole x) -> x.relationshipGroup)
					.thenComparingLong((SnomedRole x) -> x.typeId).thenComparingLong((SnomedRole x) -> x.destinationId))
					.forEach(x -> LOG.info("\t" + x));
			LOG.info("SNO concreteRolesU:");
			sno_croles_ungrouped.stream().forEach(x -> LOG.info("\t" + x));
			LOG.info("SNO rolesG:");
			sno_roles_grouped.stream().forEach(rg -> {
				LOG.info("Group:");
				rg.roles.forEach(r -> LOG.info("\t" + r));
				rg.concreteRoles.forEach(r -> LOG.info("\t" + r));
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
			mis_match_cons.add(concept.getId());
		}
	}

	private boolean compare(List<SnomedRole> sno_roles, Set<Role> nnf_roles) {
		return sno_roles.stream()
				.allMatch(sno_role -> nnf_roles.stream().anyMatch(nnf_role -> compare(sno_role, nnf_role)))
				&& nnf_roles.stream()
						.allMatch(nnf_role -> sno_roles.stream().anyMatch(sno_role -> compare(sno_role, nnf_role)));
	}

	private boolean compare(SnomedRole sno_role, Role nnf_role) {
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
