/*
 * #%L
 * elk-reasoner
 * 
 * $Id$
 * $HeadURL$
 * %%
 * Copyright (C) 2011 Oxford University Computing Laboratory
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
package org.semanticweb.elk.util.collections;

import java.util.Collection;
import java.util.Set;

/**
 * Multimap from Keys to Collection of Values
 * 
 * @author Frantisek Simancik
 * @author Yevgeny Kazakov
 *  
 * @param <Key>
 *            the keys of the multimap
 * @param <Value>
 *            the values of the multimap
 */
public interface Multimap<Key, Value> {

	/**
	 * Tests if the key-value pair occurs in this multimap
	 * 
	 * @param key
	 *            the key of the pair
	 * @param value
	 *            the value of the pair
	 * @return {@code true} if this multimap contains the pair, {@code false}
	 *         otherwise
	 */
	boolean contains(Key key, Value value);

	/**
	 * Stores the key-value pair in this multimap
	 * 
	 * @param key
	 *            the key of the pair
	 * @param value
	 *            the value of the pair
	 * @return {@code true} if the multimap has changed as a result of the
	 *         operation, {@code false} otherwise
	 */
	boolean add(Key key, Value value);
	
	/**
	 * Returns the collection of values associated with the given key in this
	 * multimap
	 * 
	 * @param key
	 *            the key for which to retrieve the values
	 * @return the collection of values associated with the given key in this
	 *         multimap
	 */
	Collection<Value> get(Key key);

	/**
	 * Removes the key-value pair from this multimap
	 * 
	 * @param key
	 *            the key of the pair
	 * @param value
	 *            the value of the pair
	 * @return {@code true} if the multimap has changed as a result of the
	 *         operation, {@code false} otherwise
	 */
	boolean remove(Object key, Object value);
	
	/**
	 * Removes all values for the given key
	 * 
	 * @param key
	 *            the key for which to remove the values
	 * @return the values that have been removed
	 */
	Collection<Value> remove(Object key);

	/**
	 * Checks if this multimap is empty
	 * 
	 * @return {@code true} if this multimap is empty, {@code false} otherwise
	 */
	boolean isEmpty();


	Set<Key> keySet();

	/**
	 * Removes all key-value pairs of this multimap
	 */
	void clear();
}
