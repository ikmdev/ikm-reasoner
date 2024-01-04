 
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class SubClassInclusionComposedSingletonObjectOneOfMatch1
		extends AbstractSubClassInclusionComposedCanonizerMatch1
		implements SubClassInclusionComposedMatch1Watch {

	private final ElkIndividual memberMatch_;

	SubClassInclusionComposedSingletonObjectOneOfMatch1(
			SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkIndividual memberMatch) {
		super(parent, destinationMatch);
		this.memberMatch_ = memberMatch;
	}

	public ElkIndividual getMemberMatch() {
		return memberMatch_;
	}

	@Override
	public SubClassInclusionComposedMatch1 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), memberMatch_);
	}

	@Override
	SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), factory.getObjectOneOf(memberMatch_));

	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			SubClassInclusionComposedMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubClassInclusionComposedSingletonObjectOneOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedSingletonObjectOneOfMatch1 getSubClassInclusionComposedSingletonObjectOneOfMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch,
				ElkIndividual memberMatch);

	}

}
