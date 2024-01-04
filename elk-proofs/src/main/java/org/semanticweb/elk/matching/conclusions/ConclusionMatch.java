
package org.semanticweb.elk.matching.conclusions;



public interface ConclusionMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				IndexedAxiomMatch.Factory,
				SaturationConclusionMatch.Factory {

		// combined interface

	}
	
	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends
				IndexedAxiomMatch.Visitor<O>,
				SaturationConclusionMatch.Visitor<O> {

		// combined interface

	}

}
