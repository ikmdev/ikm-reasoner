
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch5;
import org.semanticweb.elk.matching.inferences.BackwardLinkReversedExpandedMatch2;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch4;
import org.semanticweb.elk.matching.inferences.InferenceMatch;



public interface ForwardLinkMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch5.Visitor<O>,
			BackwardLinkReversedExpandedMatch2.Visitor<O>,
			ForwardLinkCompositionMatch4.Visitor<O> {

		// combined interface

	}

}
