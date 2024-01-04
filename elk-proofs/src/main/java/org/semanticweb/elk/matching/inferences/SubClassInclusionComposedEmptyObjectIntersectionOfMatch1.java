
package org.semanticweb.elk.matching.inferences;



import java.util.Collections;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class SubClassInclusionComposedEmptyObjectIntersectionOfMatch1
		extends AbstractSubClassInclusionComposedCanonizerMatch1
		implements SubClassInclusionComposedMatch1Watch {

	SubClassInclusionComposedEmptyObjectIntersectionOfMatch1(
			SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch) {
		super(parent, destinationMatch);
	}

	@Override
	public SubClassInclusionComposedMatch1 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), factory.getOwlThing());
	}

	@Override
	SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(getParent(),
				getDestinationMatch(), factory.getObjectIntersectionOf(
						Collections.<ElkClassExpression> emptyList()));

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

		O visit(SubClassInclusionComposedEmptyObjectIntersectionOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedEmptyObjectIntersectionOfMatch1 getSubClassInclusionComposedEmptyObjectIntersectionOfMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch);

	}

}
