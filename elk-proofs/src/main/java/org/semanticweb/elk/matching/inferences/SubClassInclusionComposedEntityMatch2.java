
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.ElkMatchException;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.SubsumerMatch;

public class SubClassInclusionComposedEntityMatch2
		extends AbstractInferenceMatch<SubClassInclusionComposedEntityMatch1> {

	private final IndexedContextRootMatch extendedOriginMatch_;

	SubClassInclusionComposedEntityMatch2(
			SubClassInclusionComposedEntityMatch1 parent,
			SubClassInclusionDecomposedMatch2 premiseMatch) {
		super(parent);
		SubsumerMatch subsumerMatch = premiseMatch.getSubsumerMatch();
		if (!parent.getConclusionSubsumerMatch().equals(subsumerMatch)) {
			throw new ElkMatchException(getParent().getParent().getSubsumer(),
					subsumerMatch);
		}
		this.extendedOriginMatch_ = premiseMatch.getExtendedDestinationMatch();
		checkEquals(premiseMatch, getPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getPremiseMatch(factory), getExtendedOriginMatch(),
				getParent().getConclusionSubsumerMatch());
	}

	public SubClassInclusionComposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParent().getConclusionMatch(factory),
				getExtendedOriginMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
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

		O visit(SubClassInclusionComposedEntityMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedEntityMatch2 getSubClassInclusionComposedEntityMatch2(
				SubClassInclusionComposedEntityMatch1 parent,
				SubClassInclusionDecomposedMatch2 premiseMatch);

	}

}
