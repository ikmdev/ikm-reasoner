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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concept {

	private long id;

	private String name;

	private List<Definition> definitions = new ArrayList<>();

	private List<Definition> gciDefinitions = new ArrayList<>();

	public Concept(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public void addDefinition(Definition definition) {
		this.definitions.add(definition);
	}

	public void removeAllDefinitions() {
		this.definitions = new ArrayList<>();
	}

	public List<Definition> getGciDefinitions() {
		return gciDefinitions;
	}

	public void addGciDefinition(Definition definition) {
		this.gciDefinitions.add(definition);
	}

	public void removeAllGciDefinitions() {
		this.gciDefinitions = new ArrayList<>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concept other = (Concept) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Concept[" + id + (name != null ? " " + name : "") + "]";
	}

}
