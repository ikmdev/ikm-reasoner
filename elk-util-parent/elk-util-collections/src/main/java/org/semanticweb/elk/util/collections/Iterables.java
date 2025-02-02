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

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Iterables {

	/**
	 * Returns a view containing the result of applying {@code function} to each
	 * element of {@code fromIterable}.
	 *
	 * <p>
	 * <b>{@code Stream} equivalent:</b> {@link Stream#map}
	 * 
	 * <p>
	 * Replacement for guava method
	 */
	public static <F extends Object, T extends Object> Iterable<T> transform(final Iterable<F> fromIterable,
			final Function<F, T> function) {
		return () -> StreamSupport.stream(fromIterable.spliterator(), false).map(function).iterator();
	}

	/**
	 * Combines multiple iterables into a single iterable. The returned iterable has
	 * an iterator that traverses the elements of each iterable in {@code inputs}.
	 * The input iterators are not polled until necessary.
	 *
	 * <p>
	 * <b>{@code Stream} equivalent:</b> {@code Streams.concat(...)}.
	 * 
	 * <p>
	 * Replacement for guava method
	 */
	@SafeVarargs
	public static <T extends Object> Iterable<T> concat(Iterable<T>... inputs) {
		return () -> Arrays.stream(Arrays.copyOf(inputs, inputs.length))
				.map(i -> StreamSupport.stream(i.spliterator(), false)).reduce(Stream::concat).get().iterator();
	}

	/**
	 * Combines multiple iterables into a single iterable. The returned iterable has
	 * an iterator that traverses the elements of each iterable in {@code inputs}.
	 * The input iterators are not polled until necessary.
	 *
	 * <p>
	 * <b>Java 8+ users:</b> The {@code Stream} equivalent of this method is {@code
	 * streamOfStreams.flatMap(s -> s)}.
	 * 
	 * <p>
	 * Replacement for guava method
	 */
	public static <T extends Object> Iterable<T> concat(Iterable<Iterable<T>> inputs) {
		return () -> StreamSupport.stream(inputs.spliterator(), false)
				.map(i -> StreamSupport.stream(i.spliterator(), false)).flatMap(s -> s).iterator();
	}

	/**
	 * Returns a string representation of {@code iterable}, with the format
	 * {@code [e1, e2, ..., en]} (that is, identical to {@link java.util.Arrays
	 * Arrays}{@code .toString(Iterables.toArray(iterable))}).
	 * 
	 * Note that for <i>most</i> implementations of {@link Collection},
	 * {@code collection.toString()} also gives the same result, but that behavior
	 * is not generally guaranteed.
	 * 
	 * <p>
	 * Replacement for guava method
	 */
	public static String toString(Iterable<?> iterable) {
		return Arrays.toString(StreamSupport.stream(iterable.spliterator(), false).toArray());
	}

}
