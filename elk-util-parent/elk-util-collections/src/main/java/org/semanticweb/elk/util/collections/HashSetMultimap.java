
package org.semanticweb.elk.util.collections;

import java.util.Collection;

/**
 * 
 * Implementation of Multimap backed by an ArrayHashMap
 * 
 * @author Frantisek Simancik
 * 
 * @param <Key>
 * @param <Value>
 */

public class HashSetMultimap<Key, Value> extends AbstractHashMultimap<Key, Value> {
	
	public HashSetMultimap() {
		super();
	}

	public HashSetMultimap(int i) {
		super(i);
	}

	@Override
	protected Collection<Value> newRecord() {
		return new ArrayHashSet<Value>();
	}
}
