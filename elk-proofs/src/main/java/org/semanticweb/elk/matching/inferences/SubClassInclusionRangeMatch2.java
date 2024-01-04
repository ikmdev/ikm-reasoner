
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch2;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

public class SubClassInclusionRangeMatch2
		extends AbstractInferenceMatch<SubClassInclusionRangeMatch1> {

	private final ElkObjectProperty propertyMatch_;

	private final ElkClassExpression conclusionSubsumerMatch_;

	SubClassInclusionRangeMatch2(SubClassInclusionRangeMatch1 parent,
			PropertyRangeMatch2 secondPremiseMatch) {
		super(parent);
		this.propertyMatch_ = secondPremiseMatch.getPropertyMatch();
		this.conclusionSubsumerMatch_ = secondPremiseMatch.getRangeMatch();
		checkEquals(secondPremiseMatch, getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public ElkClassExpression getConclusionSubsumerMatch() {
		return conclusionSubsumerMatch_;
	}

	public ElkObjectProperty getPropertyMatch() {
		return propertyMatch_;
	}

	PropertyRangeMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getPropertyRangeMatch2(
				getParent().getSecondPremiseMatch(factory), getPropertyMatch(),
				getConclusionSubsumerMatch());
	}

	public SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getConclusionMatch(factory),
				getParent().getOriginMatch()
						.extend(getConclusionSubsumerMatch()),
				getConclusionSubsumerMatch());
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

		O visit(SubClassInclusionRangeMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionRangeMatch2 getSubClassInclusionRangeMatch2(
				SubClassInclusionRangeMatch1 parent,
				PropertyRangeMatch2 secondPremiseMatch);

	}

}
