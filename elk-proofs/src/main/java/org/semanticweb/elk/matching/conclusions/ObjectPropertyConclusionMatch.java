
package org.semanticweb.elk.matching.conclusions;



public interface ObjectPropertyConclusionMatch extends ConclusionMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				PropertyRangeMatch1.Factory,
				PropertyRangeMatch2.Factory,
				SubPropertyChainMatch1.Factory,
				SubPropertyChainMatch2.Factory {

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
				PropertyRangeMatch1.Visitor<O>,
				PropertyRangeMatch2.Visitor<O>,
				SubPropertyChainMatch1.Visitor<O>,
				SubPropertyChainMatch2.Visitor<O> {

		// combined interface

	}

}
