package dev.ikm.elk.snomed.model;

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

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.MutableSet;

import java.util.Objects;
import java.util.Set;

public class Definition {
    private DefinitionType definitionType;
    
    // These will auto-optimize based on size
    private MutableSet<Concept> superConcepts = Sets.mutable.empty();
    private RoleGroup ungroupedRoles = new RoleGroup();
    private MutableSet<RoleGroup> roleGroups = Sets.mutable.empty();

	public DefinitionType getDefinitionType() {
		return definitionType;
	}

	public void setDefinitionType(DefinitionType definitionType) {
		this.definitionType = definitionType;
	}

	public MutableSet<Concept> getSuperConcepts() {
		return superConcepts;
	}

	public MutableSet<Role> getUngroupedRoles() {
		return ungroupedRoles.getRoles();
	}

	public void addUngroupedRole(Role role) {
		this.ungroupedRoles.addRole(role);
	}

	public MutableSet<ConcreteRole> getUngroupedConcreteRoles() {
		return ungroupedRoles.getConcreteRoles();
	}

	public void addUngroupedConcreteRole(ConcreteRole concreteRole) {
		this.ungroupedRoles.addConcreteRole(concreteRole);
	}

	public MutableSet<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	public void addRoleGroup(RoleGroup roleGroup) {
		this.roleGroups.add(roleGroup);
	}

    // If you KNOW the typical size, you can hint:
    // private MutableSet<Concept> superConcepts = Sets.mutable.withInitialCapacity(2);
    
    // All your existing methods work unchanged!
    public void addSuperConcept(Concept superConcept) {
        this.superConcepts.add(superConcept);  // Internally upgrades to UnifiedSet when needed
    }

	@Override
	public int hashCode() {
		return Objects.hash(definitionType, roleGroups, superConcepts, ungroupedRoles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Definition other = (Definition) obj;
		return definitionType == other.definitionType && Objects.equals(roleGroups, other.roleGroups)
				&& Objects.equals(superConcepts, other.superConcepts)
				&& Objects.equals(ungroupedRoles, other.ungroupedRoles);
	}

	public Definition copy() {
		Definition def = new Definition();
		def.definitionType = this.definitionType;
		def.roleGroups.addAll(this.roleGroups.stream().map(x -> x.copy()).toList());
		def.superConcepts.addAll(this.superConcepts);
		def.ungroupedRoles = this.ungroupedRoles.copy();
		return def;
	}

	@Override
	public String toString() {
		return getDefinitionType() + "\n" + getSuperConcepts() + "\n" + getUngroupedRoles() + "\n"
				+ getUngroupedConcreteRoles() + "\n" + getRoleGroups();
	}

}
