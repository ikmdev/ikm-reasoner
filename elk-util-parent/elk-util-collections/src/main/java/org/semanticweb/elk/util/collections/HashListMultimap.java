
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
 * @param <Value>
 */

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
