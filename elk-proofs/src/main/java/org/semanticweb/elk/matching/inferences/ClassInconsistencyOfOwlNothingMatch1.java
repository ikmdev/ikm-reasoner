
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfOwlNothing;

public class ClassInconsistencyOfOwlNothingMatch1
		extends AbstractInferenceMatch<ClassInconsistencyOfOwlNothing>
		implements SubClassInclusionComposedMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	ClassInconsistencyOfOwlNothingMatch1(ClassInconsistencyOfOwlNothing parent,
			ClassInconsistencyMatch1 conclusionMatch) {
		super(parent);
		this.originMatch_ = conclusionMatch.getDestinationMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	ClassInconsistencyMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getClassInconsistencyMatch1(
				getParent().getConclusion(factory), getOriginMatch());
	}

	public SubClassInclusionComposedMatch1 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getPremise(factory), getOriginMatch(),
				factory.getOwlNothing());
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

		O visit(ClassInconsistencyOfOwlNothingMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyOfOwlNothingMatch1 getClassInconsistencyOfOwlNothingMatch1(
				ClassInconsistencyOfOwlNothing parent,
				ClassInconsistencyMatch1 conclusionMatch);

	}

}
