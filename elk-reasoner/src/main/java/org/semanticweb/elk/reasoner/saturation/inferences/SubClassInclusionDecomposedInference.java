
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;



/**
 * A {@link SubClassInclusionInference} that produces
 * {@link SubClassInclusionDecomposed} conclusions
 * 
 * @author Yevgeny Kazakov
 */
public interface SubClassInclusionDecomposedInference
		extends SubClassInclusionInference {

	public <O> O accept(Visitor<O> visitor);

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O>
			extends
			SubClassInclusionDecomposedFirstConjunct.Visitor<O>,
			SubClassInclusionDecomposedSecondConjunct.Visitor<O>,
			SubClassInclusionExpandedDefinition.Visitor<O>,
			SubClassInclusionExpandedFirstEquivalentClass.Visitor<O>,
			SubClassInclusionExpandedSecondEquivalentClass.Visitor<O>,
			SubClassInclusionExpandedSubClassOf.Visitor<O>,
			SubClassInclusionObjectHasSelfPropertyRange.Visitor<O>,
			SubClassInclusionRange.Visitor<O>,
			SubClassInclusionTautology.Visitor<O> {

		// combined interface

	}

}
