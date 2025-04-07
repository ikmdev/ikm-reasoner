package dev.ikm.elk.snomed;

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
