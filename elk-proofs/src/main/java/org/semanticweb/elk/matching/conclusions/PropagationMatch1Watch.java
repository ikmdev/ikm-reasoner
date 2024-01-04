
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedObjectSomeValuesFromMatch2;



public interface PropagationMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends
			SubClassInclusionComposedObjectSomeValuesFromMatch2.Visitor<O> {

		// combined interface

	}

}
