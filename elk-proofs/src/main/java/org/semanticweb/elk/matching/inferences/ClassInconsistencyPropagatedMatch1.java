
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1;
import org.semanticweb.elk.matching.conclusions.BackwardLinkMatch1Watch;
import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyPropagated;

public class ClassInconsistencyPropagatedMatch1
		extends AbstractInferenceMatch<ClassInconsistencyPropagated>
		implements BackwardLinkMatch1Watch {

	private final IndexedContextRootMatch destinationMatch_;

	ClassInconsistencyPropagatedMatch1(ClassInconsistencyPropagated parent,
			ClassInconsistencyMatch1 conclusionMatch) {
		super(parent);
		this.destinationMatch_ = conclusionMatch.getDestinationMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
	}

	ClassInconsistencyMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getClassInconsistencyMatch1(
				getParent().getConclusion(factory), getDestinationMatch());
	}

	public BackwardLinkMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getBackwardLinkMatch1(
				getParent().getFirstPremise(factory), getDestinationMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(BackwardLinkMatch1Watch.Visitor<O> visitor) {
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

		O visit(ClassInconsistencyPropagatedMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyPropagatedMatch1 getClassInconsistencyPropagatedMatch1(
				ClassInconsistencyPropagated parent,
				ClassInconsistencyMatch1 conclusionMatch);

	}

}
