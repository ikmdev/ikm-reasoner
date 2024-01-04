
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkComposition;

public class BackwardLinkCompositionMatch1
		extends AbstractInferenceMatch<BackwardLinkComposition>
		implements IndexedSubObjectPropertyOfAxiomMatch1Watch {

	private final IndexedContextRootMatch conclusionSourceMatch_;

	BackwardLinkCompositionMatch1(BackwardLinkComposition parent,
			BackwardLinkMatch1 conclusionMatch) {
		super(parent);
		this.conclusionSourceMatch_ = conclusionMatch.getSourceMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getConclusionSourceMatch() {
		return conclusionSourceMatch_;
	}

	BackwardLinkMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch1(getParent().getConclusion(factory),
				getConclusionSourceMatch());
	}

	public IndexedSubObjectPropertyOfAxiomMatch1 getFifthPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedSubObjectPropertyOfAxiomMatch1(
				getParent().getFifthPremise(factory));
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

		O visit(BackwardLinkCompositionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		BackwardLinkCompositionMatch1 getBackwardLinkCompositionMatch1(
				BackwardLinkComposition parent,
				BackwardLinkMatch1 conclusionMatch);

	}

}
