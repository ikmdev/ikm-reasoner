
package org.semanticweb.elk.util.collections;

public class Triple<First, Second, Third> {
	protected final First first;
	protected final Second second;
	protected final Third third;
	
	public Triple(First first, Second second, Third third) {
		assert first != null;
		assert second != null;
		assert third != null;
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public First getFirst() {
		return first;
	}

	public Second getSecond() {
		return second;
	}

	public Third getThird() {
		return third;
	}

	@Override
	public int hashCode() {
		final int prime = 945194447;
		int result = 1;
		result = prime * result + first.hashCode();
		result = prime * result + second.hashCode();
		result = prime * result + third.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Triple<?,?,?> other = (Triple<?,?,?>) obj;
		if (!first.equals(other.first))
			return false;
		if (!second.equals(other.second))
			return false;
		if (!third.equals(other.third))
			return false;
		return true;
	}

}
