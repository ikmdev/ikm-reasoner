
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch2;
import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1;
import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1Watch;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

public class ClassInconsistencyPropagatedMatch2
		extends AbstractInferenceMatch<ClassInconsistencyPropagatedMatch1>
		implements ClassInconsistencyMatch1Watch {

	private final ElkObjectProperty premiseRelationMatch_;

	private final IndexedContextRootMatch originMatch_;

	ClassInconsistencyPropagatedMatch2(
			ClassInconsistencyPropagatedMatch1 parent,
			BackwardLinkMatch2 firstPremiseMatch) {
		super(parent);
		this.premiseRelationMatch_ = firstPremiseMatch.getRelationMatch();
		this.originMatch_ = firstPremiseMatch.getDestinationMatch();
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public ElkObjectProperty getPremiseRelationMatch() {
		return premiseRelationMatch_;
	}

	IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	BackwardLinkMatch2 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch2(
				getParent().getFirstPremiseMatch(factory),
				getPremiseRelationMatch(), getOriginMatch());
	}

	public ClassInconsistencyMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getClassInconsistencyMatch1(
				getParent().getParent().getSecondPremise(factory),
				getOriginMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ClassInconsistencyMatch1Watch.Visitor<O> visitor) {
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

		O visit(ClassInconsistencyPropagatedMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyPropagatedMatch2 getClassInconsistencyPropagatedMatch2(
				ClassInconsistencyPropagatedMatch1 parent,
				BackwardLinkMatch2 firstPremiseMatch);

	}

}
