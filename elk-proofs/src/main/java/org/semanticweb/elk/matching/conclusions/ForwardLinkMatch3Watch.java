
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch7;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch4;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch6;
import org.semanticweb.elk.matching.inferences.InferenceMatch;



public interface ForwardLinkMatch3Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch7.Visitor<O>,
			BackwardLinkReversedExpandedMatch4.Visitor<O>,
			ForwardLinkCompositionMatch6.Visitor<O> {

		// combined interface

	}

}
