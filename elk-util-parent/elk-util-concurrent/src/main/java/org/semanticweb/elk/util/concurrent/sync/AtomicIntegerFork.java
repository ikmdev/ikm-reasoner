
package org.semanticweb.elk.util.concurrent.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * An {@link AtomicInteger} through which one can modify the
 * {@link AtomicInteger} in an asynchronous way.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class AtomicIntegerFork extends AtomicInteger {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1553653698465796093L;
	final AtomicInteger parent;

	public AtomicIntegerFork(AtomicInteger parent) {
		this.parent = parent;
	}

	public AtomicInteger getParent() {
		return this.parent;
	}

	/**
	 * move the changes with this counter to the parent counter; the sums of the
	 * counters should be preserved if none of them is modified
	 */
	public void sync() {
		int snapshot = get();
		parent.addAndGet(snapshot);
		this.addAndGet(-snapshot);
	}

}
