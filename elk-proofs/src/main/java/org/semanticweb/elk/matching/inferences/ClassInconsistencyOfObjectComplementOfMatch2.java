
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;

public class ClassInconsistencyOfObjectComplementOfMatch2 extends
		AbstractInferenceMatch<ClassInconsistencyOfObjectComplementOfMatch1>
		implements SubClassInclusionComposedMatch1Watch {

	private final IndexedContextRootMatch extendedOriginMatch_;

	private final ElkObjectComplementOf negationMatch_;

	ClassInconsistencyOfObjectComplementOfMatch2(
			ClassInconsistencyOfObjectComplementOfMatch1 parent,
			SubClassInclusionDecomposedMatch2 secondPremiseMatch) {
		super(parent);
		this.extendedOriginMatch_ = secondPremiseMatch
				.getExtendedDestinationMatch();
		this.negationMatch_ = secondPremiseMatch
				.getSubsumerElkObjectComplementOfMatch();
		checkEquals(secondPremiseMatch,
				getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	public ElkObjectComplementOf getNegationMatch() {
		return negationMatch_;
	}

	public SubClassInclusionComposedMatch1 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getParent().getFirstPremise(factory),
				getExtendedOriginMatch(),
				getNegationMatch().getClassExpression());
	}

	SubClassInclusionDecomposedMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getSecondPremiseMatch(factory),
				getExtendedOriginMatch(), getNegationMatch());
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

		O visit(ClassInconsistencyOfObjectComplementOfMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyOfObjectComplementOfMatch2 getClassInconsistencyOfObjectComplementOfMatch2(
				ClassInconsistencyOfObjectComplementOfMatch1 parent,
				SubClassInclusionDecomposedMatch2 secondPremiseMatch);

	}

}
