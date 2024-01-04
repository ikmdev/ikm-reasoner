 
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionExpandedFirstEquivalentClass;

public class SubClassInclusionExpandedFirstEquivalentClassMatch1 extends
		AbstractInferenceMatch<SubClassInclusionExpandedFirstEquivalentClass>
		implements IndexedEquivalentClassesAxiomMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	SubClassInclusionExpandedFirstEquivalentClassMatch1(
			SubClassInclusionExpandedFirstEquivalentClass parent,
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

	public IndexedEquivalentClassesAxiomMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedEquivalentClassesAxiomMatch1(
				getParent().getSecondPremise(factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			IndexedEquivalentClassesAxiomMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubClassInclusionExpandedFirstEquivalentClassMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionExpandedFirstEquivalentClassMatch1 getSubClassInclusionExpandedFirstEquivalentClassMatch1(
				SubClassInclusionExpandedFirstEquivalentClass parent,
				SubClassInclusionDecomposedMatch1 conclusionMatch);

	}

}
