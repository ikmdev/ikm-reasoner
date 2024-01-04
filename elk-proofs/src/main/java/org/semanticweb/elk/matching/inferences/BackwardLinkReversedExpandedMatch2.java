
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1;
import org.semanticweb.elk.matching.conclusions.ForwardLinkMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;

public class BackwardLinkReversedExpandedMatch2
		extends AbstractInferenceMatch<BackwardLinkReversedExpandedMatch1>
		implements ForwardLinkMatch1Watch {

	private final ElkSubObjectPropertyExpression subChainMatch_;

	private final ElkObjectProperty relationMatch_;

	BackwardLinkReversedExpandedMatch2(
			BackwardLinkReversedExpandedMatch1 parent,
			IndexedSubObjectPropertyOfAxiomMatch2 secondPremiseMatch) {
		super(parent);
		this.subChainMatch_ = secondPremiseMatch.getSubPropertyChainMatch();
		this.relationMatch_ = secondPremiseMatch.getSuperPropertyMatch();
		checkEquals(secondPremiseMatch,
				getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public ElkSubObjectPropertyExpression getSubChainMatch() {
		return subChainMatch_;
	}

	public ElkObjectProperty getRelationMatch() {
		return relationMatch_;
	}

	public ForwardLinkMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getForwardLinkMatch1(
				getParent().getParent().getFirstPremise(factory),
				getParent().getOriginMatch(), subChainMatch_, 0);
	}

	IndexedSubObjectPropertyOfAxiomMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedSubObjectPropertyOfAxiomMatch2(
				getParent().getSecondPremiseMatch(factory), getSubChainMatch(),
				getRelationMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ForwardLinkMatch1Watch.Visitor<O> visitor) {
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

		O visit(BackwardLinkReversedExpandedMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkReversedExpandedMatch2 getBackwardLinkReversedExpandedMatch2(
				BackwardLinkReversedExpandedMatch1 parent,
				IndexedSubObjectPropertyOfAxiomMatch2 secondPremiseMatch);

	}

}
