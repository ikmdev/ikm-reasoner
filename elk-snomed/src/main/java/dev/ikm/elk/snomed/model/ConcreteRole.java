package dev.ikm.elk.snomed.model;

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

import java.util.Objects;

public class ConcreteRole {
	
	public static boolean convert_to_float_for_compare = false;

	public enum ValueType {
		Decimal, Double, Float, Integer, String;
	}

	private ConcreteRoleType concreteRoleType;

	private String value;

	private ValueType valueType;

	public ConcreteRole(ConcreteRoleType concreteRoleType, String value, ValueType valueType) {
		super();
		this.concreteRoleType = concreteRoleType;
		this.value = value;
		this.valueType = valueType;
		if (convert_to_float_for_compare && this.valueType == ValueType.Decimal) {
			this.value = Float.valueOf(value).toString();
			this.valueType = ValueType.Float;
		}
	}

	public ConcreteRoleType getConcreteRoleType() {
		return concreteRoleType;
	}

	public String getValue() {
		return value;
	}

	public ValueType getValueType() {
		return valueType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(concreteRoleType, value, valueType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcreteRole other = (ConcreteRole) obj;
		return Objects.equals(concreteRoleType, other.concreteRoleType) && Objects.equals(value, other.value)
				&& valueType == other.valueType;
	}

	@Override
	public String toString() {
		return "ConcreteRole[" + concreteRoleType + " -> " + this.value + " " + this.valueType + "]";
	}

}
