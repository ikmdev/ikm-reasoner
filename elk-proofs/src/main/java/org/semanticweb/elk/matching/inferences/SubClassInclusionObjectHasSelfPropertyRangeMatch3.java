
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.ElkMatchException;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch2;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

public class SubClassInclusionObjectHasSelfPropertyRangeMatch3 extends
		AbstractInferenceMatch<SubClassInclusionObjectHasSelfPropertyRangeMatch2> {

	private final ElkClassExpression rangeMatch_;

	SubClassInclusionObjectHasSelfPropertyRangeMatch3(
			SubClassInclusionObjectHasSelfPropertyRangeMatch2 parent,
			PropertyRangeMatch2 secondPremiseMatch) {
		super(parent);
		this.rangeMatch_ = secondPremiseMatch.getRangeMatch();
		ElkObjectProperty propertyMatch = secondPremiseMatch.getPropertyMatch();
		if (!parent.getPropertyMatch().equals(propertyMatch)) {
			throw new ElkMatchException(parent.getParent().getParent()
					.getPremiseSubsumer().getProperty(), propertyMatch);
		}
		checkEquals(secondPremiseMatch, getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public ElkClassExpression getRangeMatch() {
		return rangeMatch_;
	}

	PropertyRangeMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getPropertyRangeMatch2(
				getParent().getSecondPremiseMatch(factory),
				getParent().getPropertyMatch(), getRangeMatch());
	}

	public SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getParent().getExtendedOriginMatch(), getRangeMatch());
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

		O visit(SubClassInclusionObjectHasSelfPropertyRangeMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionObjectHasSelfPropertyRangeMatch3 getSubClassInclusionObjectHasSelfPropertyRangeMatch3(
				SubClassInclusionObjectHasSelfPropertyRangeMatch2 parent,
				PropertyRangeMatch2 secondPremiseMatch);

	}

}
