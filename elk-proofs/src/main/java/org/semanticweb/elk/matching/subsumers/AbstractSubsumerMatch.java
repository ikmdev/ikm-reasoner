
package org.semanticweb.elk.matching.subsumers;



abstract class AbstractSubsumerMatch implements SubsumerMatch {

	/**
	 * hash code, computed on demand
	 */
	private int hashCode_ = 0;

	@Override
	public int hashCode() {
		if (hashCode_ == 0) {
			hashCode_ = SubsumerMatchHash.hashCode(this);
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
		if (o instanceof SubsumerMatch) {
			return hashCode() == o.hashCode()
					&& SubsumerMatchEquality.equals(this, (SubsumerMatch) o);
		}
		// else
		return false;
	}

	@Override
	public String toString() {
		return SubsumerMatchPrinter.toString(this);
	}

}
