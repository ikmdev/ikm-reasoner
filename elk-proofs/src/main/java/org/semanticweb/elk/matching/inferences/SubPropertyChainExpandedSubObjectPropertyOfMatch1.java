
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubPropertyChainMatch1;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.SubPropertyChainExpandedSubObjectPropertyOf;

public class SubPropertyChainExpandedSubObjectPropertyOfMatch1 extends
		AbstractInferenceMatch<SubPropertyChainExpandedSubObjectPropertyOf>
		implements IndexedSubObjectPropertyOfAxiomMatch1Watch {

	private final ElkSubObjectPropertyExpression fullSuperChainMatch_;

	private final int superChainStartPos_;

	SubPropertyChainExpandedSubObjectPropertyOfMatch1(
			SubPropertyChainExpandedSubObjectPropertyOf parent,
			SubPropertyChainMatch1 conclusionMatch) {
		super(parent);
		fullSuperChainMatch_ = conclusionMatch.getFullSuperChainMatch();
		superChainStartPos_ = conclusionMatch.getSuperChainStartPos();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	public ElkSubObjectPropertyExpression getFullSuperChainMatch() {
		return fullSuperChainMatch_;
	}

	public int getSuperChainStartPos() {
		return superChainStartPos_;
	}

	SubPropertyChainMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubPropertyChainMatch1(
				getParent().getConclusion(factory), getFullSuperChainMatch(),
				getSuperChainStartPos());

	}

	public IndexedSubObjectPropertyOfAxiomMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedSubObjectPropertyOfAxiomMatch1(
				getParent().getFirstPremise(factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			IndexedSubObjectPropertyOfAxiomMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubPropertyChainExpandedSubObjectPropertyOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubPropertyChainExpandedSubObjectPropertyOfMatch1 getSubPropertyChainExpandedSubObjectPropertyOfMatch1(
				SubPropertyChainExpandedSubObjectPropertyOf parent,
				SubPropertyChainMatch1 conclusionMatch);

	}

}
