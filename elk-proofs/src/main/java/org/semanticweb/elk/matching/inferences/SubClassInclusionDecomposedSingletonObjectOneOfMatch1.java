
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;

public class SubClassInclusionDecomposedSingletonObjectOneOfMatch1
		extends AbstractSubClassInclusionDecomposedCanonizerMatch1 {

	private final ElkIndividual memberMatch_;

	SubClassInclusionDecomposedSingletonObjectOneOfMatch1(
			SubClassInclusionDecomposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch,
			ElkIndividual memberMatch) {
		super(parent, extendedDestinationMatch);
		this.memberMatch_ = memberMatch;
	}

	public ElkIndividual getMemberMatch() {
		return memberMatch_;
	}

	@Override
	SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(getParent(),
				getExtendedDestinationMatch(),
				factory.getObjectOneOf(memberMatch_));
	}

	@Override
	public SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(getParent(),
				getExtendedDestinationMatch(), memberMatch_);
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

		O visit(SubClassInclusionDecomposedSingletonObjectOneOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionDecomposedSingletonObjectOneOfMatch1 getSubClassInclusionDecomposedSingletonObjectOneOfMatch1(
				SubClassInclusionDecomposedMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch,
				ElkIndividual memberMatch);

	}

}
