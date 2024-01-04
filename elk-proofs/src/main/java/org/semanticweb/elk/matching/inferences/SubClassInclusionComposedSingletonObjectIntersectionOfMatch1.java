
package org.semanticweb.elk.matching.inferences;



import java.util.Collections;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class SubClassInclusionComposedSingletonObjectIntersectionOfMatch1
		extends AbstractSubClassInclusionComposedCanonizerMatch1
		implements SubClassInclusionComposedMatch1Watch {

	private final ElkClassExpression conjunctMatch_;

	SubClassInclusionComposedSingletonObjectIntersectionOfMatch1(
			SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkClassExpression conjunctMatch) {
		super(parent, destinationMatch);
		this.conjunctMatch_ = conjunctMatch;
	}

	public ElkClassExpression getConjunctMatch() {
		return conjunctMatch_;
	}

	@Override
	public SubClassInclusionComposedMatch1 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), conjunctMatch_);
	}

	@Override
	SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), factory.getObjectIntersectionOf(
						Collections.singletonList(conjunctMatch_)));
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

		O visit(SubClassInclusionComposedSingletonObjectIntersectionOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedSingletonObjectIntersectionOfMatch1 getSubClassInclusionComposedSingletonObjectIntersectionOfMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch,
				ElkClassExpression conjunctMatch);

	}

}
