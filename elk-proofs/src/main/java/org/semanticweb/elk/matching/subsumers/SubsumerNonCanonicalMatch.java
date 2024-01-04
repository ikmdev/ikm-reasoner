
package org.semanticweb.elk.matching.subsumers;



public interface SubsumerNonCanonicalMatch extends SubsumerElkObjectMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O>
			extends SubsumerEmptyObjectIntersectionOfMatch.Visitor<O>,
			SubsumerEmptyObjectOneOfMatch.Visitor<O>,
			SubsumerEmptyObjectUnionOfMatch.Visitor<O>,
			SubsumerObjectHasValueMatch.Visitor<O>,
			SubsumerSingletonObjectIntersectionOfMatch.Visitor<O>,
			SubsumerSingletonObjectOneOfMatch.Visitor<O>,
			SubsumerSingletonObjectUnionOfMatch.Visitor<O> {

		// combined interface
	}

}
