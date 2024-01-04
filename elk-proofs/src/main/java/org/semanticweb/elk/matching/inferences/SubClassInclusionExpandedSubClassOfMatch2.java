
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

public class SubClassInclusionExpandedSubClassOfMatch2 extends
		AbstractInferenceMatch<SubClassInclusionExpandedSubClassOfMatch1>
		implements SubClassInclusionComposedMatch1Watch {

	private final ElkClassExpression premiseSubsumerMatch_,
			conclusionSubsumerMatch_;

	SubClassInclusionExpandedSubClassOfMatch2(
			SubClassInclusionExpandedSubClassOfMatch1 parent,
			IndexedSubClassOfAxiomMatch2 secondPremiseMatch) {
		super(parent);
		this.premiseSubsumerMatch_ = secondPremiseMatch.getSubClassMatch();
		this.conclusionSubsumerMatch_ = secondPremiseMatch.getSuperClassMatch();
		checkEquals(secondPremiseMatch, getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public ElkClassExpression getPremiseSubsumerMatch() {
		return premiseSubsumerMatch_;
	}

	public ElkClassExpression getConclusionSubsumerMatch() {
		return conclusionSubsumerMatch_;
	}

	public SubClassInclusionComposedMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getParent().getFirstPremise(factory),
				getParent().getOriginMatch(), getPremiseSubsumerMatch());
	}

	IndexedSubClassOfAxiomMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedSubClassOfAxiomMatch2(
				getParent().getSecondPremiseMatch(factory),
				getPremiseSubsumerMatch(), getConclusionSubsumerMatch());
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

		O visit(SubClassInclusionExpandedSubClassOfMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionExpandedSubClassOfMatch2 getSubClassInclusionExpandedSubClassOfMatch2(
				SubClassInclusionExpandedSubClassOfMatch1 parent,
				IndexedSubClassOfAxiomMatch2 secondPremiseMatch);

	}

}
