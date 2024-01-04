
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionObjectHasSelfPropertyRangeMatch2;
import org.semanticweb.elk.matching.inferences.SubClassInclusionRangeMatch1;



public interface PropertyRangeMatch1Watch extends InferenceMatch {

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
			SubClassInclusionObjectHasSelfPropertyRangeMatch2.Visitor<O>,
			SubClassInclusionRangeMatch1.Visitor<O> {

		// combined interface

	}

}
