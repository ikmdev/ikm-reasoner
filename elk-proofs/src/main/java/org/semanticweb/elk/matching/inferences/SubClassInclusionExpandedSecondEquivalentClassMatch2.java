
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch2;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

public class SubClassInclusionExpandedSecondEquivalentClassMatch2 extends
		AbstractInferenceMatch<SubClassInclusionExpandedSecondEquivalentClassMatch1>
		implements SubClassInclusionComposedMatch1Watch {

	private final ElkClassExpression premiseSubsumerMatch_,
			conclusionSubsumerMatch_;

	SubClassInclusionExpandedSecondEquivalentClassMatch2(
			SubClassInclusionExpandedSecondEquivalentClassMatch1 parent,
			IndexedEquivalentClassesAxiomMatch2 secondPremiseMatch) {
		super(parent);
		this.conclusionSubsumerMatch_ = secondPremiseMatch
				.getFirstMemberMatch();
		this.premiseSubsumerMatch_ = secondPremiseMatch.getSecondMemberMatch();
		checkEquals(secondPremiseMatch, getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public ElkClassExpression getConclusionSubsumerMatch() {
		return conclusionSubsumerMatch_;
	}

	public ElkClassExpression getPremiseSubsumerMatch() {
		return premiseSubsumerMatch_;
	}

	public SubClassInclusionComposedMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getParent().getFirstPremise(factory),
				getParent().getOriginMatch(), getPremiseSubsumerMatch());
	}

	IndexedEquivalentClassesAxiomMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedEquivalentClassesAxiomMatch2(
				getParent().getSecondPremiseMatch(factory),
				getConclusionSubsumerMatch(), getPremiseSubsumerMatch());
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

		O visit(SubClassInclusionExpandedSecondEquivalentClassMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionExpandedSecondEquivalentClassMatch2 getSubClassInclusionExpandedSecondEquivalentClassMatch2(
				SubClassInclusionExpandedSecondEquivalentClassMatch1 parent,
				IndexedEquivalentClassesAxiomMatch2 secondPremiseMatch);

	}

}
