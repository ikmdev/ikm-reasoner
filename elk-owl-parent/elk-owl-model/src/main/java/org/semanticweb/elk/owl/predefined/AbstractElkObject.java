
package org.semanticweb.elk.owl.predefined;



import org.semanticweb.elk.owl.comparison.ElkObjectEquality;
import org.semanticweb.elk.owl.comparison.ElkObjectHash;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.printers.OwlFunctionalStylePrinter;

/**
 * A skeleton implementation of {@link ElkObject} defining equality, hash, and
 * similar functions. All other implementation should extend or delegate to this
 * class.
 * 
 * @author Yevgeny Kazakov
 */
public abstract class AbstractElkObject implements ElkObject {

	/**
	 * hash code, computed on demand
	 */
	private int hashCode_ = 0;

	@Override
	public final int hashCode() {
		if (hashCode_ == 0) {
			hashCode_ = accept(ElkObjectHash.getInstance());
		}
		// else
		return hashCode_;
	}

	@Override
	public final boolean equals(Object o) {
		return this == o || (o != null && hashCode() == o.hashCode()
				&& accept(new ElkObjectEquality(o)) != null);
	}

	@Override
	public String toString() {
		return OwlFunctionalStylePrinter.toString(this);
	}

}
