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

import java.nio.file.Path;
import java.util.HashMap;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRole;
import dev.ikm.elk.snomed.model.ConcreteRoleType;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;

public class SnomedLoader {

	private HashMap<Long, RoleType> role_types = new HashMap<>();

	private HashMap<Long, ConcreteRoleType> concrete_role_types = new HashMap<>();

	private HashMap<Long, Concept> concepts = new HashMap<>();

	public SnomedOntology load(Path concepts_file, Path descriptions_file, Path relationships_file, Path values_file)
			throws Exception {
		SnomedConcepts snomed_concepts = SnomedConcepts.init(concepts_file);
		SnomedDescriptions descriptions = SnomedDescriptions.init(descriptions_file);
		SnomedIsa isa = SnomedIsa.init(relationships_file);
		SnomedRoles roles = SnomedRoles.init(relationships_file);
		SnomedConcreteRoles values = SnomedConcreteRoles.init(values_file);
		load(snomed_concepts, descriptions, isa, roles, values);
		return new SnomedOntology(concepts.values(), role_types.values(), concrete_role_types.values());
	}

	private void load(SnomedConcepts snomed_concepts, SnomedDescriptions descr, SnomedIsa isa, SnomedRoles roles,
			SnomedConcreteRoles values) {
		for (long id : isa.getDescendants(SnomedIds.concept_model_object_attribute)) {
			RoleType role_type = new RoleType(id);
			role_type.setName(descr.getFsn(id));
			role_types.put(id, role_type);
		}
		for (long id : isa.getDescendants(SnomedIds.concept_model_data_attribute)) {
			ConcreteRoleType role_type = new ConcreteRoleType(id);
			role_type.setName(descr.getFsn(id));
			concrete_role_types.put(id, role_type);
		}
		for (long id : isa.getOrderedConcepts()) {
			Concept con = new Concept(id);
			con.setName(descr.getFsn(id));
			concepts.put(id, con);
		}
		for (long id : isa.getOrderedConcepts()) {
			Concept con = concepts.get(id);
			Definition def = new Definition();
			con.addDefinition(def);
			DefinitionType dt = snomed_concepts.getDefinitionStatus(id).getDefinitionType();
			def.setDefinitionType(dt);
			isa.getParents(id).forEach(parent -> def.addSuperConcept(concepts.get(parent)));
			roles.getUngroupedRoles(id).forEach(role -> def
					.addUngroupedRole(new Role(role_types.get(role.typeId), concepts.get(role.destinationId))));
			values.getUngroupedConcreteRoles(id)
					.forEach(role -> def.addUngroupedConcreteRole(
							new ConcreteRole(concrete_role_types.get(role.typeId), role.value.replace("#", ""),
									(concrete_role_types.get(role.typeId).getName().startsWith("Count")
											? ConcreteRole.ValueType.Integer
											: ConcreteRole.ValueType.Decimal))));
			SnomedRoleGroups.getRoleGroups(id, roles, values).forEach(srg -> {
				RoleGroup rg = new RoleGroup();
				def.addRoleGroup(rg);
				srg.roles.forEach(
						role -> rg.addRole(new Role(role_types.get(role.typeId), concepts.get(role.destinationId))));
				srg.concreteRoles.forEach(role -> rg.addConcreteRole(
						new ConcreteRole(concrete_role_types.get(role.typeId), role.value.replace("#", ""),
								(concrete_role_types.get(role.typeId).getName().startsWith("Count")
										? ConcreteRole.ValueType.Integer
										: ConcreteRole.ValueType.Decimal))));
			});
		}
	}

}
