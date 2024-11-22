package dev.ikm.elk.snomed.owlel;

/*-
 * #%L
 * SNOMED OWL EL Profile Model and Parser
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.ikm.elk.snomed.owlel.model.OwlElClass;
import dev.ikm.elk.snomed.owlel.model.OwlElDataProperty;
import dev.ikm.elk.snomed.owlel.model.OwlElObjectProperty;

public class OwlElObjectFactory {

	private final HashMap<String, OwlElClass> classesMap = new HashMap<>();

	private final HashMap<String, OwlElDataProperty> dataPropertiesMap = new HashMap<>();

	private final HashMap<String, OwlElObjectProperty> objectPropertiesMap = new HashMap<>();

	public List<OwlElClass> getClasses() {
		return new ArrayList<>(classesMap.values());
	}

	public List<OwlElDataProperty> getDataProperties() {
		return new ArrayList<>(dataPropertiesMap.values());
	}

	public List<OwlElObjectProperty> getObjectProperties() {
		return new ArrayList<>(objectPropertiesMap.values());
	}

	public OwlElClass getOwlElClass(String iri) {
		return classesMap.computeIfAbsent(iri, OwlElClass::new);
	}

	public OwlElDataProperty getOwlElDataProperty(String iri) {
		return dataPropertiesMap.computeIfAbsent(iri, OwlElDataProperty::new);
	}

	public OwlElObjectProperty getOwlElObjectProperty(String iri) {
		return objectPropertiesMap.computeIfAbsent(iri, OwlElObjectProperty::new);
	}

}
