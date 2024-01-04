
package org.semanticweb.elk.util.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ArraySet<T> extends ArrayList<T> implements Set<T> {

	private static final long serialVersionUID = 4210562273973502066L;

	public ArraySet() {
		super();
	}
	
	public ArraySet(Collection<? extends T> c) {
		this(c.size());
		addAll(c);
	}
	
	public ArraySet(int initialCapacity) {
		super(initialCapacity);
	}
	
	@Override
	public boolean add(T element) {
		if (!contains(element)) 
			return super.add(element);
		return false;
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean change = false;
		for (T element : c)
			change = this.add(element) || change;
		return change;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}
}
