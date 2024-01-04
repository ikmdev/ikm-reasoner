
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;

public class SubClassInclusionComposedSingletonObjectUnionOfMatch2 extends
		AbstractSubClassInclusionComposedCanonizerMatch2<SubClassInclusionComposedSingletonObjectUnionOfMatch1> {

	SubClassInclusionComposedSingletonObjectUnionOfMatch2(
			SubClassInclusionComposedSingletonObjectUnionOfMatch1 parent,
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

		O visit(SubClassInclusionComposedSingletonObjectUnionOfMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedSingletonObjectUnionOfMatch2 getSubClassInclusionComposedSingletonObjectUnionOfMatch2(
				SubClassInclusionComposedSingletonObjectUnionOfMatch1 parent,
				SubClassInclusionComposedMatch2 premiseMatch);

	}

}
