
package org.semanticweb.elk.matching.subsumers;



public interface SubsumerMatch {

	public <O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> extends SubsumerElkObjectMatch.Visitor<O>,
			IndexedObjectIntersectionOfMatch.Visitor<O> {

		// combined interface

	}

}
