package dev.ikm.elk.snomed;

/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2025 Integrated Knowledge Management
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;

public class ConceptComparer {

	private static final Logger LOG = LoggerFactory.getLogger(ConceptComparer.class);

	private SnomedOntology inferredOntology;

	private HashSet<Long> mis_match_cons = new HashSet<>();

	private int mis_match_cnt = 0;

	private boolean log_mis_match_detail = false;

	public ConceptComparer(SnomedOntology inferredOntology) {
		this.inferredOntology = inferredOntology;
	}

	public int getMisMatchCount() {
		return mis_match_cnt;
	}

	public boolean compare(Concept concept) {
		return compare(inferredOntology.getConcept(concept.getId()), concept);
	}

	public boolean compare(Concept concept1, Concept concept2) {
		log_mis_match_detail = concept1.getDefinitions().size() == 1 & concept2.getDefinitions().size() == 1
				&& concept1.getGciDefinitions().isEmpty() && concept2.getGciDefinitions().isEmpty();
		boolean match = true;
		if (!compare(concept1, concept1.getDefinitions(), concept2.getDefinitions(), "1")) {
			match = false;
		}
		if (!compare(concept1, concept2.getDefinitions(), concept1.getDefinitions(), "2")) {
			match = false;
		}
		if (!compare(concept1, concept1.getGciDefinitions(), concept2.getGciDefinitions(), "1-gci")) {
			match = false;
		}
		if (!compare(concept1, concept2.getGciDefinitions(), concept1.getGciDefinitions(), "2-gci")) {
			match = false;
		}
		if (!match) {
			mis_match_cnt++;
			mis_match_cons.add(concept1.getId());
			if (log_mis_match_detail) {
				LOG.info("Concept: " + concept1);
				LOG.info("Concept1 parents:");
				concept1.getDefinitions().getFirst().getSuperConcepts().stream().forEach(x -> LOG.info("\t" + x));
				LOG.info("Concept1 rolesU:");
				concept1.getDefinitions().getFirst().getUngroupedRoles().stream().forEach(x -> LOG.info("\t" + x));
				LOG.info("Concept1 concreteRolesU:");
				concept1.getDefinitions().getFirst().getUngroupedConcreteRoles().stream()
						.forEach(x -> LOG.info("\t" + x));
				LOG.info("Concept1 rolesG:");
				concept1.getDefinitions().getFirst().getRoleGroups().stream().forEach(rg -> {
					LOG.info("Group:");
					rg.getRoles().forEach(r -> LOG.info("\t" + r));
					rg.getConcreteRoles().forEach(y -> LOG.info("\t" + y));
				});
				//
				LOG.info("Concept2 parents:");
				concept2.getDefinitions().getFirst().getSuperConcepts().stream().forEach(x -> LOG.info("\t" + x));
				LOG.info("Concept2 rolesU:");
				concept2.getDefinitions().getFirst().getUngroupedRoles().forEach(x -> LOG.info("\t" + x));
				LOG.info("Concept2 concreteRolesU:");
				concept2.getDefinitions().getFirst().getUngroupedConcreteRoles().forEach(x -> LOG.info("\t" + x));
				LOG.info("Concept2 rolesG:");
				concept2.getDefinitions().getFirst().getRoleGroups().forEach(rg -> {
					LOG.info("Group:");
					rg.getRoles().forEach(r -> LOG.info("\t" + r));
					rg.getConcreteRoles().forEach(y -> LOG.info("\t" + y));
				});
			}
		}
		return match;
	}

	private boolean compare(Concept concept, List<Definition> definitions1, List<Definition> definitions2, String tag) {
		boolean match = true;
		for (Definition definition1 : definitions1) {
			boolean match1 = definitions2.stream().anyMatch(definition2 -> compare(concept, definition1, definition2));
			if (!match1) {
				if (log_mis_match_detail)
					LOG.error("No concept" + tag + " definition for " + concept + " " + definition1);
				match = false;
			}
		}
		return match;
	}

	private boolean compare(Concept concept, Definition definition1, Definition definition2) {
		boolean match = true;
		if (definition1.getDefinitionType() != definition2.getDefinitionType()) {
			if (log_mis_match_detail)
				LOG.error("Definition type: " + concept + " " + definition1.getDefinitionType() + " "
						+ definition2.getDefinitionType());
			match = false;
		}
		if (!compareSuperConcepts(concept, definition1.getSuperConcepts(), definition2.getSuperConcepts(), "1"))
			match = false;
		if (!compareSuperConcepts(concept, definition2.getSuperConcepts(), definition1.getSuperConcepts(), "2"))
			match = false;
		if (!compareUngrouped(concept, definition1.getUngroupedRoles(), definition2.getUngroupedRoles(), "1"))
			match = false;
		if (!compareUngrouped(concept, definition2.getUngroupedRoles(), definition1.getUngroupedRoles(), "2"))
			match = false;
		if (!compareUngroupedConcrete(concept, definition1.getUngroupedConcreteRoles(),
				definition2.getUngroupedConcreteRoles(), "1"))
			match = false;
		if (!compareUngroupedConcrete(concept, definition2.getUngroupedConcreteRoles(),
				definition1.getUngroupedConcreteRoles(), "2"))
			match = false;
		if (!compareRoleGroups(concept, definition1.getRoleGroups(), definition2.getRoleGroups(), "1"))
			match = false;
		if (!compareRoleGroups(concept, definition2.getRoleGroups(), definition1.getRoleGroups(), "2"))
			match = false;
		return match;
	}

	private boolean compareSuperConcepts(Concept concept, Set<Concept> sups1, Set<Concept> sups2, String tag) {
		boolean match = true;
		for (Concept sup1 : sups1) {
			boolean match1 = sups2.stream().anyMatch(sup2 -> sup1.getId() == sup2.getId());
			if (!match1) {
				if (log_mis_match_detail)
					LOG.error("No concept" + tag + " superC for " + concept + " " + sup1);
				match = false;
			}
		}
		return match;
	}

	private boolean compareUngrouped(Concept concept, Set<Role> roles1, Set<Role> roles2, String tag) {
		boolean match = true;
		for (Role role1 : roles1) {
			boolean match1 = roles2.stream().anyMatch(role2 -> compare(role1, role2));
			if (!match1) {
				if (log_mis_match_detail)
					LOG.error("No concept" + tag + " roleU for " + concept + " " + role1);
				match = false;
			}
		}
		return match;
	}

	private boolean compareUngroupedConcrete(Concept concept, Set<ConcreteRole> roles1, Set<ConcreteRole> roles2,
			String tag) {
		boolean match = true;
		for (ConcreteRole role1 : roles1) {
			boolean match1 = roles2.stream().anyMatch(role2 -> compare(role1, role2));
			if (!match1) {
				if (log_mis_match_detail)
					LOG.error("No concept" + tag + " roleU for " + concept + " " + role1);
				match = false;
			}
		}
		return match;
	}

	private boolean compareRoleGroups(Concept concept, Set<RoleGroup> roles1, Set<RoleGroup> roles2, String tag) {
		boolean match = true;
		for (RoleGroup role1 : roles1) {
			boolean match1 = roles2.stream().anyMatch(role2 -> compare(role1.getRoles(), role2.getRoles())
					&& compareConcrete(role1.getConcreteRoles(), role2.getConcreteRoles()));
			if (!match1) {
				if (log_mis_match_detail)
					LOG.error("No concept" + tag + " roleG for " + concept + " " + role1);
				match = false;
			}
		}
		return match;
	}

	private boolean compare(Set<Role> roles1, Set<Role> roles2) {
		return roles1.stream().allMatch(role1 -> roles2.stream().anyMatch(role2 -> compare(role1, role2)))
				&& roles2.stream().allMatch(role2 -> roles1.stream().anyMatch(role1 -> compare(role1, role2)));
	}

	private boolean compare(Role role1, Role role2) {
		return role1.getRoleType().getId() == role2.getRoleType().getId()
				&& role1.getConcept().getId() == role2.getConcept().getId();
	}

	private boolean compareConcrete(Set<ConcreteRole> roles1, Set<ConcreteRole> roles2) {
		return roles1.stream().allMatch(role1 -> roles2.stream().anyMatch(role2 -> compare(role1, role2)))
				&& roles2.stream().allMatch(role2 -> roles1.stream().anyMatch(role1 -> compare(role1, role2)));
	}

	private boolean compare(ConcreteRole role1, ConcreteRole role2) {
		return role1.getConcreteRoleType().getId() == role2.getConcreteRoleType().getId()
				&& role1.getValueType() == role2.getValueType() && role1.getValue().equals(role2.getValue());
	}

}
