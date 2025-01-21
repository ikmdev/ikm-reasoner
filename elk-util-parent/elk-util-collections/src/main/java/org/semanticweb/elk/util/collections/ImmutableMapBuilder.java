package org.semanticweb.elk.util.collections;

/*-
 * #%L
 * ELK Utilities Collections
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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Replacement for guava ImmutableMap.builder()
 * 
 * @param <K>
 * @param <V>
 */
public class ImmutableMapBuilder<K, V> {

	private LinkedHashMap<K, V> map = new LinkedHashMap<>();

	private ImmutableMapBuilder() {
		super();
	}

	/**
	 * Returns a new builder. The generated builder is equivalent to the builder
	 * created by the {@link Builder} constructor.
	 */
	public static <K, V> ImmutableMapBuilder<K, V> builder() {
		return new ImmutableMapBuilder<>();
	}

	public ImmutableMapBuilder<K, V> put(K key, V value) {
		map.put(key, value);
		return this;
	}

	public Map<K, V> build() {
		return Collections.unmodifiableMap(map);
	}

}
