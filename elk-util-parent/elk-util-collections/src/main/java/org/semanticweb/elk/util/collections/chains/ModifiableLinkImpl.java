
package org.semanticweb.elk.util.collections.chains;



/**
 * A simple implementation of the {@link Link} and {@link ModifiableLink}
 * interfaces. The methods are not thread safe.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            The types of elements in the chain.
 */
public class ModifiableLinkImpl<T> implements ModifiableLink<T> {

	/**
	 * the field to store the next element
	 */
	private T next_ = null;

	public ModifiableLinkImpl() {
	}

	public ModifiableLinkImpl(T next) {
		this.next_ = next;
	}

	@Override
	public T next() {
		return next_;
	}

	@Override
	public void setNext(T next) {
		next_ = next;
	}

}
