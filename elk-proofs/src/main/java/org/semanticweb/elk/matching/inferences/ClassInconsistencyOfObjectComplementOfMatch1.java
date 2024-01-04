
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfObjectComplementOf;

public class ClassInconsistencyOfObjectComplementOfMatch1
		extends AbstractInferenceMatch<ClassInconsistencyOfObjectComplementOf>
		implements SubClassInclusionDecomposedMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	ClassInconsistencyOfObjectComplementOfMatch1(
			ClassInconsistencyOfObjectComplementOf parent,
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

	public SubClassInclusionDecomposedMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch1(
				getParent().getSecondPremise(factory), getOriginMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			SubClassInclusionDecomposedMatch1Watch.Visitor<O> visitor) {
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

		O visit(ClassInconsistencyOfObjectComplementOfMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyOfObjectComplementOfMatch1 getClassInconsistencyOfObjectComplementOfMatch1(
				ClassInconsistencyOfObjectComplementOf parent,
				ClassInconsistencyMatch1 conclusionMatch);

	}

}
