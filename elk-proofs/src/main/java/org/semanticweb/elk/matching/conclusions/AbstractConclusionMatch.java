
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.matching.AbstractMatch;

public abstract class AbstractConclusionMatch<P> extends AbstractMatch<P>
		implements ConclusionMatch {

	/**
	 * hash code, computed on demand
	 */
	private int hashCode_ = 0;

	AbstractConclusionMatch(P parent) {
		super(parent);
	}

	@Override
	public int hashCode() {
		if (hashCode_ == 0) {
			hashCode_ = accept(ConclusionMatchHash.getInstance());
		}
		// else
		return hashCode_;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		// else
		if (o instanceof ConclusionMatch) {
			return hashCode() == o.hashCode() && ConclusionMatchEquality
					.equals(this, (ConclusionMatch) o);
		}
		// else
		return false;
	}

	@Override
	public String toString() {
		return ConclusionMatchPrinter.toString(this);
	}

}
