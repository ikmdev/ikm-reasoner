
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch8;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyPropagatedMatch3;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch7;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectSomeValuesFromMatch3;



public interface BackwardLinkMatch3Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch8.Visitor<O>,
			ClassInconsistencyPropagatedMatch3.Visitor<O>,
			ForwardLinkCompositionMatch7.Visitor<O>,
			SubClassInclusionComposedObjectSomeValuesFromMatch3.Visitor<O> {

		// combined interface

	}

}
