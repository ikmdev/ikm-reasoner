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

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Iterators {

	/**
	 * Combines two iterators into a single iterator. The returned iterator iterates
	 * across the elements in {@code a}, followed by the elements in {@code b}. The
	 * source iterators are not polled until necessary.
	 *
	 * <p>
	 * The returned iterator supports {@code remove()} when the corresponding input
	 * iterator supports it.
	 * 
	 * <p>
	 * Replacement for guava method
	 */
	public static <T extends Object> Iterator<T> concat(Iterator<T> iter1, Iterator<T> iter2) {
		return Stream.concat(StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter1, 0), false),
				StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter2, 0), false)).iterator();
	}

}
