
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedSubClassOfMatch1;



public interface IndexedSubClassOfAxiomMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends SubClassInclusionExpandedSubClassOfMatch1.Visitor<O> {

		// combined interface

	}

}
