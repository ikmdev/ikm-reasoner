
package org.semanticweb.elk.util.collections;

import java.util.Collection;
import java.util.Collections;

/**
 * 
 * Implementation of Multimap backed by an ArrayHashMap
 * 
 * @author Frantisek Simancik
 * 
 * @param <Key>
 * @param <Value>
 */

public abstract class AbstractHashMultimap<Key, Value> extends
		ArrayHashMap<Key, Collection<Value>> implements Multimap<Key, Value> {

	protected abstract Collection<Value> newRecord();

	public AbstractHashMultimap() {
		super();
	}

	public AbstractHashMultimap(int i) {
		super(i);
	}

	@Override
	public boolean contains(Key key, Value value) {
		Collection<Value> record = super.get(key);
		if (record == null)
			return false;
		// else
		return record.contains(value);
	}

	@Override
	public boolean add(Key key, Value value) {
		Collection<Value> record = super.get(key);
		if (record == null) {
			record = newRecord();
			put(key, record);
		}
		return record.add(value);
	}

	@Override
	public Collection<Value> get(Object key) {
		Collection<Value> result = super.get(key);
		if (result == null)
			return Collections.emptyList();
		return result;
	}

	/**
	 * @param key
	 * @return the collection of values associated with the given key, or
	 *         {@code null} if no value us associated
	 */
	public Collection<Value> getValues(Object key) {
		return super.get(key);
	}

	@Override
	public boolean remove(Object key, Object value) {
		Collection<Value> record = super.get(key);
		if (record == null)
			return false;
		if (record.remove(value)) {
			if (record.isEmpty())
				super.remove(key);
			return true;
		}
		// else
		return false;
	}

}
