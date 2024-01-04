
package org.semanticweb.elk.reasoner.tracing;



/**
 * A skeleton implementation of {@link Conclusion} with defined structural hash
 * and equality
 * 
 * @author Yevgeny Kazakov
 *
 */
public abstract class AbstractConclusion implements Conclusion {

	/**
	 * hash code, computed on demand
	 */
	private int hashCode_ = 0;

	@Override
	public int hashCode() {
		if (hashCode_ == 0) {
			hashCode_ = ConclusionHash.hashCode(this);
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
		if (o instanceof Conclusion) {
			return hashCode() == o.hashCode()
					&& ConclusionEquality.equals(this, (Conclusion) o);
		}
		// else
		return false;
	}

	@Override
	public String toString() {
		return ConclusionPrinter.toString(this);
	}

}
