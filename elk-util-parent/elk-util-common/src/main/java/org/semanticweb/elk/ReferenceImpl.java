
package org.semanticweb.elk;



/**
 * A simple implementation of {@link ModifiableReference}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <O>
 *            the type of the value of this {@link Reference}
 */
public class ReferenceImpl<O> implements ModifiableReference<O> {

	private O object_;

	@Override
	public O get() {
		return object_;
	}

	@Override
	public void set(O object) {
		this.object_ = object;
	}

}
