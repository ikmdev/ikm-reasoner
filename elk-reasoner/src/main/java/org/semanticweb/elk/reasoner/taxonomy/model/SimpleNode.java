package org.semanticweb.elk.reasoner.taxonomy.model;
/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2013 Department of Computer Science, University of Oxford
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Peter Skocovsky
 *
 * @param <T>
 */
public class SimpleNode<T> implements Node<T> {

	protected final List<T> members_;
	protected final ComparatorKeyProvider<? super T> keyProvider_;

	public SimpleNode(final Iterable<T> members, final int size,
			final ComparatorKeyProvider<? super T> keyProvider) {
		if (keyProvider == null) {
			throw new IllegalArgumentException("keyProvider cannot be null!");
		}
		keyProvider_ = keyProvider;
		if (members == null || size <= 0) {
			this.members_ = new ArrayList<T>();
		} else {
			this.members_ = new ArrayList<T>(size);
			for (T member : members) {
				this.members_.add(member);
			}
			Collections.sort(this.members_, getKeyProvider().getComparator());
		}
	}
	
	public SimpleNode(final ComparatorKeyProvider<T> comparatorKeyProvider) {
		this(null, 0, comparatorKeyProvider);
	}
	
	@Override
	public ComparatorKeyProvider<? super T> getKeyProvider() {
		return keyProvider_;
	}
	
	@Override
	public Iterator<T> iterator() {
		return members_.iterator();
	}
	
	@Override
	public boolean contains(final T member) {
		return (Collections.binarySearch(members_, member,
				getKeyProvider().getComparator()) >= 0);
	}
	
	@Override
	public int size() {
		return members_.size();
	}
	
	@Override
	public T getCanonicalMember() {
		return members_.isEmpty() ? null : members_.get(0);
	}

	@Override
	public String toString() {
		return members_.toString();
	}
	
}
