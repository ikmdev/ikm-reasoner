
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch2;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1Watch;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;

public class SubPropertyChainExpandedSubObjectPropertyOfMatch2 extends
		AbstractInferenceMatch<SubPropertyChainExpandedSubObjectPropertyOfMatch1>
		implements SubPropertyChainMatch1Watch {

	private final ElkSubObjectPropertyExpression subChainMatch_;

	private final ElkObjectProperty interPropertyMatch_;

	SubPropertyChainExpandedSubObjectPropertyOfMatch2(
			SubPropertyChainExpandedSubObjectPropertyOfMatch1 parent,
			IndexedSubObjectPropertyOfAxiomMatch2 firstPremiseMatch) {
		super(parent);
		subChainMatch_ = firstPremiseMatch.getSubPropertyChainMatch();
		interPropertyMatch_ = firstPremiseMatch.getSuperPropertyMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public ElkSubObjectPropertyExpression getSubChainMatch() {
		return subChainMatch_;
	}

	public ElkObjectProperty getInterPropertyMatch() {
		return interPropertyMatch_;
	}

	IndexedSubObjectPropertyOfAxiomMatch2 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedSubObjectPropertyOfAxiomMatch2(
				getParent().getFirstPremiseMatch(factory), getSubChainMatch(),
				getInterPropertyMatch());
	}

	public SubPropertyChainMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch1(
				getParent().getParent().getSecondPremise(factory),
				getParent().getFullSuperChainMatch(),
				getParent().getSuperChainStartPos());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(SubPropertyChainMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubPropertyChainExpandedSubObjectPropertyOfMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubPropertyChainExpandedSubObjectPropertyOfMatch2 getSubPropertyChainExpandedSubObjectPropertyOfMatch2(
				SubPropertyChainExpandedSubObjectPropertyOfMatch1 parent,
				IndexedSubObjectPropertyOfAxiomMatch2 firstPremiseMatch);

	}

}
