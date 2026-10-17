package dev.ikm.elk.snomed.model;

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

/**
 * An existential restriction whose filler is itself an expression, {@code ∃R.(C ⊓ ∃S.D …)}, rather
 * than a concept: the filler is a {@link Definition}'s content (its definition type unused). Kept
 * apart from {@link Role}, whose filler is always a concept, so code that reads
 * {@link Role#getConcept()} never meets one; the reasoner classifies with nested roles, and the
 * necessary normal form does not yet carry them.
 *
 * @param roleType the role
 * @param filler   what the role's values are: concepts, roles, nested roles, concrete roles
 */
public record NestedRole(RoleType roleType, Definition filler) {

	public NestedRole copy() {
		return new NestedRole(roleType, filler.copy());
	}
}
