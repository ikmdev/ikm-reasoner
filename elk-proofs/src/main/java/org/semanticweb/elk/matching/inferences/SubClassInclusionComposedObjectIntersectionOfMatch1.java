
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.IndexedObjectIntersectionOfMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedObjectIntersectionOf;

public class SubClassInclusionComposedObjectIntersectionOfMatch1 extends
		AbstractInferenceMatch<SubClassInclusionComposedObjectIntersectionOf>
		implements SubClassInclusionComposedMatch1Watch {

	private final IndexedContextRootMatch originMatch_;
	private final ElkObjectIntersectionOf fullSubsumerMatch_;
	private final int conclusionSubsumerPrefixLength_;

	SubClassInclusionComposedObjectIntersectionOfMatch1(
			SubClassInclusionComposedObjectIntersectionOf parent,
			SubClassInclusionComposedMatch1 conclusionMatch) {
		super(parent);
		originMatch_ = conclusionMatch.getDestinationMatch();
		IndexedObjectIntersectionOfMatch conclusionSubsumerMatch = conclusionMatch
				.getSubsumerIndexedObjectIntersectionOfMatch();
		fullSubsumerMatch_ = conclusionSubsumerMatch.getFullValue();
		conclusionSubsumerPrefixLength_ = conclusionSubsumerMatch
				.getPrefixLength();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	public ElkObjectIntersectionOf getFullSubsumerMatch() {
		return fullSubsumerMatch_;
	}

	public int getConclusionSubsumerPrefixLength() {
		return conclusionSubsumerPrefixLength_;
	}

	public SubClassInclusionComposedMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getSecondPremise(factory), getOriginMatch(),
				getFullSubsumerMatch().getClassExpressions()
						.get(getConclusionSubsumerPrefixLength() - 1));
	}

	SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getConclusion(factory), getOriginMatch(),
				getFullSubsumerMatch(), getConclusionSubsumerPrefixLength());
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

		O visit(SubClassInclusionComposedObjectIntersectionOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedObjectIntersectionOfMatch1 getSubClassInclusionComposedObjectIntersectionOfMatch1(
				SubClassInclusionComposedObjectIntersectionOf parent,
				SubClassInclusionComposedMatch1 conclusionMatch);

	}

}
