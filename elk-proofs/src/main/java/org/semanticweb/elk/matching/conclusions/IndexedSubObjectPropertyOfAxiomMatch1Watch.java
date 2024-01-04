
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch1;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubPropertyChainExpandedSubObjectPropertyOfMatch1;



public interface IndexedSubObjectPropertyOfAxiomMatch1Watch
		extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch1.Visitor<O>,
			BackwardLinkReversedExpandedMatch1.Visitor<O>,
			SubPropertyChainExpandedSubObjectPropertyOfMatch1.Visitor<O> {

		// combined interface

	}

}
