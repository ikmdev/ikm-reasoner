
package org.semanticweb.elk.matching.subsumers;



public interface IndexedObjectUnionOfMatch extends SubsumerElkObjectMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> extends SubsumerObjectUnionOfMatch.Visitor<O>,
			SubsumerObjectOneOfMatch.Visitor<O> {

		// combined interface
	}

}
