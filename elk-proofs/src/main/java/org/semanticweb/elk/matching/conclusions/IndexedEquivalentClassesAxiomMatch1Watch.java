
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.SubClassInclusionComposedDefinedClassMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedDefinitionMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedFirstEquivalentClassMatch1;
import org.semanticweb.elk.matching.inferences.SubClassInclusionExpandedSecondEquivalentClassMatch1;



public interface IndexedEquivalentClassesAxiomMatch1Watch
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
	interface Visitor<O>
			extends SubClassInclusionComposedDefinedClassMatch1.Visitor<O>,
			SubClassInclusionExpandedDefinitionMatch1.Visitor<O>,
			SubClassInclusionExpandedFirstEquivalentClassMatch1.Visitor<O>,
			SubClassInclusionExpandedSecondEquivalentClassMatch1.Visitor<O> {

		// combined interface

	}

}
