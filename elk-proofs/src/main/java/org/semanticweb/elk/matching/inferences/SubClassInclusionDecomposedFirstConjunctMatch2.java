
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.IndexedObjectIntersectionOfMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;

public class SubClassInclusionDecomposedFirstConjunctMatch2 extends
		AbstractInferenceMatch<SubClassInclusionDecomposedFirstConjunctMatch1> {

	private final IndexedContextRootMatch extendedOriginMatch_;

	private final ElkObjectIntersectionOf fullSubsumerMatch_;

	private final int premiseSubsumerPrefixLength_;

	SubClassInclusionDecomposedFirstConjunctMatch2(
			SubClassInclusionDecomposedFirstConjunctMatch1 parent,
			SubClassInclusionDecomposedMatch2 premiseMatch) {
		super(parent);
		this.extendedOriginMatch_ = premiseMatch.getExtendedDestinationMatch();
		IndexedObjectIntersectionOfMatch premiseSubsumerMatch = premiseMatch
				.getSubsumerIndexedObjectIntersectionOfMatch();
		this.fullSubsumerMatch_ = premiseSubsumerMatch.getFullValue();
		this.premiseSubsumerPrefixLength_ = premiseSubsumerMatch
				.getPrefixLength();
		checkEquals(premiseMatch, getPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	public ElkObjectIntersectionOf getFullSubsumerMatch() {
		return fullSubsumerMatch_;
	}

	public int getPremiseSubsumerPrefixLength() {
		return premiseSubsumerPrefixLength_;
	}

	SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getPremiseMatch(factory), getExtendedOriginMatch(),
				getFullSubsumerMatch(), getPremiseSubsumerPrefixLength());
	}

	public SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getConclusionMatch(factory),
				getExtendedOriginMatch(), getFullSubsumerMatch(),
				getPremiseSubsumerPrefixLength() - 1);
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

		O visit(SubClassInclusionDecomposedFirstConjunctMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionDecomposedFirstConjunctMatch2 getSubClassInclusionDecomposedFirstConjunctMatch2(
				SubClassInclusionDecomposedFirstConjunctMatch1 parent,
				SubClassInclusionDecomposedMatch2 premiseMatch);

	}

}
