
package org.semanticweb.elk.matching.inferences;



import java.util.Collections;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class SubClassInclusionComposedSingletonObjectUnionOfMatch1
		extends AbstractSubClassInclusionComposedCanonizerMatch1
		implements SubClassInclusionComposedMatch1Watch {

	private final ElkClassExpression disjunctMatch_;

	SubClassInclusionComposedSingletonObjectUnionOfMatch1(
			SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkClassExpression disjunctMatch) {
		super(parent, destinationMatch);
		this.disjunctMatch_ = disjunctMatch;
	}

	public ElkClassExpression getDisjunctMatch() {
		return disjunctMatch_;
	}

	@Override
	public SubClassInclusionComposedMatch1 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), disjunctMatch_);
	}

	@Override
	SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), factory.getObjectUnionOf(
						Collections.singletonList(disjunctMatch_)));

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

		O visit(SubClassInclusionComposedSingletonObjectUnionOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedSingletonObjectUnionOfMatch1 getSubClassInclusionComposedSingletonObjectUnionOfMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch,
				ElkClassExpression disjunctMatch);

	}

}
