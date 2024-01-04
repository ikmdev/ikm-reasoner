
package org.semanticweb.elk.matching.subsumers;



public interface IndexedClassEntityMatch extends SubsumerElkObjectMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> extends IndexedClassMatch.Visitor<O>,
			IndexedIndividualMatch.Visitor<O> {

		// combined interface
	}

}
