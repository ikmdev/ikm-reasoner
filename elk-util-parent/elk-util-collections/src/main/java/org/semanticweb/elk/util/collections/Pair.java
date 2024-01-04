
package org.semanticweb.elk.util.collections;

public class Pair<First, Second> {
	protected final First first;
	protected final Second second;
	
	public static <First, Second> Pair<First, Second> create(First f, Second s) {
		return new Pair<First, Second>(f, s);
	}
	
	public Pair(First first, Second second) {
		assert first != null;
		assert second != null;
		this.first = first;
		this.second = second;
	}

	public First getFirst() {
		return first;
	}

	public Second getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		final int prime = 307967729;
		int result = 1;
		result = prime * result + first.hashCode();
		result = prime * result + second.hashCode();
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
		
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (!first.equals(other.first))
			return false;
		if (!second.equals(other.second))
			return false;
		return true;
	}
}
