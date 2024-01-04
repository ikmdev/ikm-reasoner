
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.BackwardLinkCompositionMatch2;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyPropagatedMatch1;
import org.semanticweb.elk.matching.inferences.ForwardLinkCompositionMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectSomeValuesFromMatch1;



public interface BackwardLinkMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends BackwardLinkCompositionMatch2.Visitor<O>,
			ClassInconsistencyPropagatedMatch1.Visitor<O>,
			ForwardLinkCompositionMatch1.Visitor<O>,
			SubClassInclusionComposedObjectSomeValuesFromMatch1.Visitor<O> {

		// combined interface

	}

}
