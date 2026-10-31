/*-
 * #%L
 * ELK Integration with SNOMED
 * %%
 * Copyright (C) 2023 - 2026 Integrated Knowledge Management
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
package dev.ikm.elk.snomed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Set;

import org.eclipse.collections.api.factory.primitive.LongSets;
import org.junit.jupiter.api.Test;

import dev.ikm.elk.snomed.model.Concept;
import dev.ikm.elk.snomed.model.Definition;
import dev.ikm.elk.snomed.model.DefinitionType;
import dev.ikm.elk.snomed.model.Role;
import dev.ikm.elk.snomed.model.RoleType;

/**
 * A concept with no definition is a class with no axiom: the reasoner places it under owl:Thing,
 * which no taxonomy lists, so the taxonomy places it directly under the root, and it and every
 * concept whose definition names it get a necessary normal form.
 */
public class NecessaryNormalFormUndefinedConceptTest {

	private static final long ROOT = 1;

	private static Definition necessary(Concept... parents) {
		Definition def = new Definition();
		def.setDefinitionType(DefinitionType.SubConcept);
		for (Concept parent : parents)
			def.addSuperConcept(parent);
		return def;
	}

	@Test
	public void aConceptWithoutADefinitionIsPrimitiveUnderTheRoot() {
		RoleType r = new RoleType(11);
		Concept root = new Concept(ROOT), undefined = new Concept(2), kind = new Concept(3),
				naming = new Concept(4);
		kind.addDefinition(necessary(undefined));
		Definition namingDef = necessary(root);
		namingDef.addUngroupedRole(new Role(r, undefined));
		naming.addDefinition(namingDef);
		SnomedOntology ontology = new SnomedOntology(List.of(root, undefined, kind, naming), List.of(r),
				List.of());
		SnomedOntologyReasoner reasoner = SnomedOntologyReasoner.create(ontology);

		NecessaryNormalFormBuilder nnfb = NecessaryNormalFormBuilder.create(ontology,
				reasoner.getSuperConcepts(), reasoner.getSuperRoleTypes(false), ROOT, (_, _) -> {
				});
		nnfb.generate();

		assertEquals(LongSets.immutable.of(ROOT), nnfb.getIsa().getParents(undefined.getId()));
		assertEquals(4, nnfb.getConcepts().size(), "every concept is ordered");
		Definition undefinedNnf = nnfb.getNecessaryNormalForm(undefined);
		assertNotNull(undefinedNnf);
		assertEquals(DefinitionType.SubConcept, undefinedNnf.getDefinitionType());
		assertEquals(Set.of(root), undefinedNnf.getSuperConcepts());
		assertEquals(Set.of(undefined), nnfb.getNecessaryNormalForm(kind).getSuperConcepts());
		assertNotNull(nnfb.getNecessaryNormalForm(naming));
	}
}
