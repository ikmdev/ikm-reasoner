
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;

public class SubClassInclusionComposedObjectHasValueMatch2 extends
		AbstractSubClassInclusionComposedCanonizerMatch2<SubClassInclusionComposedObjectHasValueMatch1> {

	SubClassInclusionComposedObjectHasValueMatch2(
			SubClassInclusionComposedObjectHasValueMatch1 parent,
			SubClassInclusionComposedMatch2 premiseMatch) {
		super(parent, premiseMatch);
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> {

		O visit(SubClassInclusionComposedObjectHasValueMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedObjectHasValueMatch2 getSubClassInclusionComposedObjectHasValueMatch2(
				SubClassInclusionComposedObjectHasValueMatch1 parent,
				SubClassInclusionComposedMatch2 premiseMatch);

	}

}
