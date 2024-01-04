
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public abstract class AbstractSubClassInclusionComposedCanonizerMatch2<P extends AbstractSubClassInclusionComposedCanonizerMatch1>
		extends AbstractInferenceMatch<P> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	AbstractSubClassInclusionComposedCanonizerMatch2(P parent,
			SubClassInclusionComposedMatch2 premiseMatch) {
		super(parent);
		this.extendedDestinationMatch_ = premiseMatch
				.getExtendedDestinationMatch();
		checkEquals(premiseMatch, getPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	SubClassInclusionComposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getPremiseMatch(factory),
				getExtendedDestinationMatch());
	}

	public SubClassInclusionComposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getConclusionMatch(factory),
				getExtendedDestinationMatch());
	}

}
