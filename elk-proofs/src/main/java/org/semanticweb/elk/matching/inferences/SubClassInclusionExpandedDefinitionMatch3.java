
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.ElkMatchException;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkClass;

public class SubClassInclusionExpandedDefinitionMatch3 extends
		AbstractInferenceMatch<SubClassInclusionExpandedDefinitionMatch2> {

	private final IndexedContextRootMatch extendedOriginMatch_;

	SubClassInclusionExpandedDefinitionMatch3(
			SubClassInclusionExpandedDefinitionMatch2 parent,
			SubClassInclusionDecomposedMatch2 firstPremiseMatch) {
		super(parent);
		ElkClass firstSubsumer = firstPremiseMatch.getSubsumerElkClassMatch();
		if (!parent.getDefinedClassMatch().equals(firstSubsumer)) {
			throw new ElkMatchException(
					getParent().getParent().getParent().getDefinedClass(),
					firstSubsumer);
		}
		this.extendedOriginMatch_ = firstPremiseMatch
				.getExtendedDestinationMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	SubClassInclusionDecomposedMatch2 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getFirstPremiseMatch(factory),
				getExtendedOriginMatch(), getParent().getDefinedClassMatch());
	}

	public SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getExtendedOriginMatch(), getParent().getDefinitionMatch());
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

		O visit(SubClassInclusionExpandedDefinitionMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionExpandedDefinitionMatch3 getSubClassInclusionExpandedDefinitionMatch3(
				SubClassInclusionExpandedDefinitionMatch2 parent,
				SubClassInclusionDecomposedMatch2 firstPremiseMatch);

	}

}
