
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;

public class SubClassInclusionComposedEmptyObjectIntersectionOfMatch2 extends
		AbstractSubClassInclusionComposedCanonizerMatch2<SubClassInclusionComposedEmptyObjectIntersectionOfMatch1> {

	SubClassInclusionComposedEmptyObjectIntersectionOfMatch2(
			SubClassInclusionComposedEmptyObjectIntersectionOfMatch1 parent,
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

		O visit(SubClassInclusionComposedEmptyObjectIntersectionOfMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedEmptyObjectIntersectionOfMatch2 getSubClassInclusionComposedEmptyObjectIntersectionOfMatch2(
				SubClassInclusionComposedEmptyObjectIntersectionOfMatch1 parent,
				SubClassInclusionComposedMatch2 premiseMatch);

	}

}
