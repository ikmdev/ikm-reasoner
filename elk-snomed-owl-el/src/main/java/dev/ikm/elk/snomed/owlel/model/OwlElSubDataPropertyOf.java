package dev.ikm.elk.snomed.owlel.model;

/*-
 * #%L
 * elk-snomed-owl-el
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

public class OwlElSubDataPropertyOf extends OwlElAxiom {

	private OwlElDataPropertyExpression subPropertyExpression;

	private OwlElDataPropertyExpression superPropertyExpression;

	public OwlElDataPropertyExpression getSubProperty() {
		return subPropertyExpression;
	}

	public OwlElDataPropertyExpression getSuperProperty() {
		return superPropertyExpression;
	}

	public OwlElSubDataPropertyOf(OwlElDataPropertyExpression subPropertyExpression,
			OwlElDataPropertyExpression superPropertyExpression) {
		super();
		this.subPropertyExpression = subPropertyExpression;
		this.superPropertyExpression = superPropertyExpression;
	}

	@Override
	public String toString() {
		return "SubDataPropertyOf(" + subPropertyExpression + " " + superPropertyExpression + ")";
	}

}
