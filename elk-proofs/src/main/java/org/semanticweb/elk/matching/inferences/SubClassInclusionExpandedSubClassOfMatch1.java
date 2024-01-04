
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionExpandedSubClassOf;

public class SubClassInclusionExpandedSubClassOfMatch1
		extends AbstractInferenceMatch<SubClassInclusionExpandedSubClassOf>
		implements IndexedSubClassOfAxiomMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	SubClassInclusionExpandedSubClassOfMatch1(
			SubClassInclusionExpandedSubClassOf parent,
			SubClassInclusionDecomposedMatch1 conclusionMatch) {
		super(parent);
		originMatch_ = conclusionMatch.getDestinationMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	SubClassInclusionDecomposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch1(
				getParent().getConclusion(factory), originMatch_);
	}

	public IndexedSubClassOfAxiomMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedSubClassOfAxiomMatch1(
				getParent().getSecondPremise(factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(IndexedSubClassOfAxiomMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubClassInclusionExpandedSubClassOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionExpandedSubClassOfMatch1 getSubClassInclusionExpandedSubClassOfMatch1(
				SubClassInclusionExpandedSubClassOf parent,
				SubClassInclusionDecomposedMatch1 conclusionMatch);

	}

}
