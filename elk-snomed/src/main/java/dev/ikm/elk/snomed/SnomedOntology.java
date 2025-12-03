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

import java.util.Collection;
import java.util.List;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.primitive.LongObjectMaps;
import org.eclipse.collections.api.factory.primitive.LongSets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableLongSet;

import dev.ikm.elk.snomed.model.AnnotationType;
import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.ConcreteRoleType;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleGroup;
import dev.ikm.elk.snomed.model.RoleType;
import dev.ikm.elk.snomed.owlel.OwlElOntology;

public class SnomedOntology {

	// Use primitive maps to avoid boxing/unboxing overhead
	private MutableLongObjectMap<Concept> conceptIdMap;

	private MutableList<Concept> concepts;

	private MutableLongObjectMap<RoleType> roleTypeIdMap;

	private MutableList<RoleType> roleTypes;

	private MutableLongObjectMap<ConcreteRoleType> concreteRoleTypeIdMap;

	private MutableList<ConcreteRoleType> concreteRoleTypes;

	private MutableLongObjectMap<AnnotationType> annotationTypeIdMap;

	private MutableList<AnnotationType> annotationTypes;

	private SnomedDescriptions descriptions;

	public Concept getConcept(long id) {
		return conceptIdMap.get(id);
	}

	public RoleType getRoleType(long id) {
		return roleTypeIdMap.get(id);
	}

	public ConcreteRoleType getConcreteRoleType(long id) {
		return concreteRoleTypeIdMap.get(id);
	}

	public AnnotationType getAnnotationType(long id) {
		return annotationTypeIdMap.get(id);
	}

	public List<Concept> getConcepts() {
		return concepts;
	}

	public List<RoleType> getRoleTypes() {
		return roleTypes;
	}

	public List<ConcreteRoleType> getConcreteRoleTypes() {
		return concreteRoleTypes;
	}

	public List<AnnotationType> getAnnotationTypes() {
		return annotationTypes;
	}

	public SnomedDescriptions getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(SnomedDescriptions descriptions) {
		this.descriptions = descriptions;
	}

	public SnomedOntology(Collection<Concept> concepts, Collection<RoleType> roleTypes,
			Collection<ConcreteRoleType> concreteRoleTypes) {
		this(concepts, roleTypes, concreteRoleTypes, List.of());
	}

	public SnomedOntology(Collection<Concept> concepts, Collection<RoleType> roleTypes,
			Collection<ConcreteRoleType> concreteRoleTypes, Collection<AnnotationType> annotationTypes) {
		super();
		// Use Eclipse Collections primitive maps with initial capacity
		this.conceptIdMap = LongObjectMaps.mutable.withInitialCapacity(concepts.size());
		this.concepts = Lists.mutable.withInitialCapacity(concepts.size());
		concepts.forEach(x -> {
			conceptIdMap.put(x.getId(), x);
			this.concepts.add(x);
		});
		
		this.roleTypeIdMap = LongObjectMaps.mutable.withInitialCapacity(roleTypes.size());
		this.roleTypes = Lists.mutable.withAll(roleTypes);
		roleTypes.forEach(x -> roleTypeIdMap.put(x.getId(), x));
		
		this.concreteRoleTypeIdMap = LongObjectMaps.mutable.withInitialCapacity(concreteRoleTypes.size());
		this.concreteRoleTypes = Lists.mutable.withAll(concreteRoleTypes);
		concreteRoleTypes.forEach(x -> concreteRoleTypeIdMap.put(x.getId(), x));
		
		this.annotationTypeIdMap = LongObjectMaps.mutable.withInitialCapacity(annotationTypes.size());
		this.annotationTypes = Lists.mutable.withAll(annotationTypes);
		annotationTypes.forEach(x -> annotationTypeIdMap.put(x.getId(), x));
	}

	public void addConcept(Concept concept) {
		if (conceptIdMap.containsKey(concept.getId()))
			return;
		conceptIdMap.put(concept.getId(), concept);
		concepts.add(concept);
	}

	public void addRoleType(RoleType role) {
		if (roleTypeIdMap.containsKey(role.getId()))
			return;
		roleTypeIdMap.put(role.getId(), role);
		roleTypes.add(role);
	}

	public void addConcreteRoleType(ConcreteRoleType role) {
		if (concreteRoleTypeIdMap.containsKey(role.getId()))
			return;
		concreteRoleTypeIdMap.put(role.getId(), role);
		concreteRoleTypes.add(role);
	}

	public String getFsn(long concept) {
		if (descriptions != null)
			return descriptions.getFsn(concept);
		return "<" + concept + ">";
	}

	public void setNames() {
		if (descriptions == null)
			return;
		concepts.forEach(x -> x.setName(getFsn(x.getId())));
		roleTypes.forEach(x -> x.setName(getFsn(x.getId())));
		concreteRoleTypes.forEach(x -> x.setName(getFsn(x.getId())));
		annotationTypes.forEach(x -> x.setName(getFsn(x.getId())));
	}

	public MutableSet<Concept> getDependentOnConcepts(Concept concept) {
		return getDependentOnConcepts(concept, true, true);
	}

	public MutableLongSet getDependentOnConcepts(long concept) {
		return getDependentOnConcepts(concept, true, true);
	}

	public MutableLongSet getDependentOnConcepts(long concept, boolean includeSuperConcepts, boolean includeGcis) {
		// Use primitive long set and collectLong for better performance
		MutableSet<Concept> conceptDeps = getDependentOnConcepts(getConcept(concept), includeSuperConcepts, includeGcis);
		MutableLongSet result = LongSets.mutable.withInitialCapacity(conceptDeps.size());
		conceptDeps.forEach(c -> result.add(c.getId()));
		return result;
	}

	public MutableSet<Concept> getDependentOnConcepts(Concept concept, boolean includeSuperConcepts, boolean includeGcis) {
		MutableSet<Concept> deps = Sets.mutable.empty();
		for (Definition def : concept.getDefinitions()) {
			deps.addAll(getDependentOnConcepts(def, includeSuperConcepts));
		}
		if (includeGcis) {
			for (Definition def : concept.getGciDefinitions()) {
				deps.addAll(getDependentOnConcepts(def, includeSuperConcepts));
			}
		}
		return deps;
	}

	public MutableSet<Concept> getDependentOnConcepts(Definition def, boolean includeSuperConcepts) {
		MutableSet<Concept> deps = Sets.mutable.empty();
		if (includeSuperConcepts) {
			deps.addAll(def.getSuperConcepts());
		}
		for (Role role : def.getUngroupedRoles()) {
			deps.add(role.getConcept());
		}
		for (RoleGroup rg : def.getRoleGroups()) {
			for (Role role : rg.getRoles()) {
				deps.add(role.getConcept());
			}
		}
		return deps;
	}

	public static SnomedOntology load(List<String> exprs) {
		OwlElOntology ontology = new OwlElOntology();
		ontology.load(exprs);
		SnomedOntology snomedOntology = new OwlElTransformer().transform(ontology);
		return snomedOntology;
	}

}
