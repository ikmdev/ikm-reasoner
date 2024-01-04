 
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch4;



import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch2;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class ClassInconsistencyPropagatedMatch4
		extends AbstractInferenceMatch<ClassInconsistencyPropagatedMatch3> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	ClassInconsistencyPropagatedMatch4(
			ClassInconsistencyPropagatedMatch3 parent,
			BackwardLinkMatch4 firstPremiseMatch) {
		super(parent);
		this.extendedDestinationMatch_ = firstPremiseMatch
				.getExtendedSourceMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	BackwardLinkMatch4 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch4(
				getParent().getFirstPremiseMatch(factory),
				getExtendedDestinationMatch());
	}

	public ClassInconsistencyMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getClassInconsistencyMatch2(
				getParent().getParent().getParent().getConclusionMatch(factory),
				getExtendedDestinationMatch());
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

		O visit(ClassInconsistencyPropagatedMatch4 inferenceMatch4);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyPropagatedMatch4 getClassInconsistencyPropagatedMatch4(
				ClassInconsistencyPropagatedMatch3 parent,
				BackwardLinkMatch4 firstPremiseMatch);

	}

}
