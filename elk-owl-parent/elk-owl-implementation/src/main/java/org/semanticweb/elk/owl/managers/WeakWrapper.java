 
package org.semanticweb.elk.owl.managers;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class WeakWrapper<T> extends WeakReference<T> {
	private final int hash_;
	
	protected abstract int hashCode(T referent);
	
	protected abstract boolean equal(T referent, Object obj);
		
	public WeakWrapper(T referent, ReferenceQueue<? super T> q) {
		super(referent, q);
		assert referent != null;
		hash_ = hashCode(referent);
	}

	@Override
	public int hashCode() {
		return hash_;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj instanceof WeakWrapper<?>) {
			T a = this.get();
			Object b = ((WeakWrapper<?>) obj).get();
			
			if (a != null && b != null)
				return equal(a, b);
		}
		return false;
	}
}