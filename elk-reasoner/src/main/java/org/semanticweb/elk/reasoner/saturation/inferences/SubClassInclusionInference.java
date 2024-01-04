
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;

/**
 * A {@link ClassInference} that produces {@link SubClassInclusion}s
 * 
 * @author Yevgeny Kazakov
 */
public interface SubClassInclusionInference extends ClassInference {

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
				SubClassInclusionComposedInference.Visitor<O>,
				SubClassInclusionDecomposedInference.Visitor<O> {

		// combined interface

	}

}
