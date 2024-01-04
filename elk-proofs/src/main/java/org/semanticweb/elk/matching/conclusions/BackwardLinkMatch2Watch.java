
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch6;
import org.semanticweb.elk.matching.inferences.BackwardLinkOfObjectHasSelfMatch2;
import org.semanticweb.elk.matching.inferences.BackwardLinkOfObjectSomeValuesFromMatch2;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch3;
import org.semanticweb.elk.matching.inferences.InferenceMatch;



public interface BackwardLinkMatch2Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch6.Visitor<O>,
			BackwardLinkOfObjectHasSelfMatch2.Visitor<O>,
			BackwardLinkOfObjectSomeValuesFromMatch2.Visitor<O>,
			BackwardLinkReversedExpandedMatch3.Visitor<O> {

		// combined interface

	}

}
