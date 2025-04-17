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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SnomedRoleGroups {

	public static class SnomedRoleGroup {

		public List<SnomedRoles.SnomedRole> roles = new ArrayList<>();
		public List<SnomedConcreteRoles.SnomedConcreteRole> concreteRoles = new ArrayList<>();

	}

	public static List<SnomedRoleGroup> getRoleGroups(long con, SnomedRoles roles, SnomedConcreteRoles concrete_roles) {
		HashMap<Long, SnomedRoleGroup> groups = new HashMap<>();
		for (SnomedRoles.SnomedRole role : roles.getGroupedRoles(con)) {
			groups.putIfAbsent(role.relationshipGroup, new SnomedRoleGroup());
			groups.get(role.relationshipGroup).roles.add(role);
		}
		for (SnomedConcreteRoles.SnomedConcreteRole role : concrete_roles.getGroupedConcreteRoles(con)) {
			groups.putIfAbsent(role.relationshipGroup, new SnomedRoleGroup());
			groups.get(role.relationshipGroup).concreteRoles.add(role);
		}
		return new ArrayList<>(groups.values());
	}

}
