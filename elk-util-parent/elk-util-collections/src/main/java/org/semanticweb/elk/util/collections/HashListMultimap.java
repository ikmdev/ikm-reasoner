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

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * Implementation of Multimap backed by an ArrayHashMap
 * 
 * @author Frantisek Simancik
 * 
 * @param <Key>
 *            the keys of the multimap
 * @param <Value>
 *            the values of the multimap
 */
@Deprecated
public class HashListMultimap<Key, Value> extends
		AbstractHashMultimap<Key, Value> {

	public HashListMultimap() {
		super();
	}

	public HashListMultimap(int i) {
		super(i);
	}

	@Override
	protected Collection<Value> newRecord() {
		return new ArrayList<Value>();
	}

}
