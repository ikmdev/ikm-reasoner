
package org.semanticweb.elk.matching.conclusions;



public interface SaturationConclusionMatch extends ConclusionMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ClassConclusionMatch.Factory,
				ObjectPropertyConclusionMatch.Factory {

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
				ClassConclusionMatch.Visitor<O>,
				ObjectPropertyConclusionMatch.Visitor<O> {

		// combined interface

	}

}
