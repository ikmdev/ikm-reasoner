
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectHasValue;

public class SubClassInclusionDecomposedObjectHasValueMatch1
		extends AbstractSubClassInclusionDecomposedCanonizerMatch1 {

	private final ElkObjectHasValue premiseSubsumerMatch_;

	SubClassInclusionDecomposedObjectHasValueMatch1(
			SubClassInclusionDecomposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch,
			ElkObjectHasValue premiseSubsumerMatch) {
		super(parent, extendedDestinationMatch);
		this.premiseSubsumerMatch_ = premiseSubsumerMatch;
	}

	public ElkObjectHasValue getPremiseSubsumerMatch() {
		return premiseSubsumerMatch_;
	}

	@Override
	SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(getParent(),
				getExtendedDestinationMatch(), premiseSubsumerMatch_);

	}

	@Override
	public SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(getParent(),
				getExtendedDestinationMatch(),
				factory.getObjectSomeValuesFrom(
						premiseSubsumerMatch_.getProperty(),
						factory.getObjectOneOf(
								premiseSubsumerMatch_.getFiller())));
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

		O visit(SubClassInclusionDecomposedObjectHasValueMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionDecomposedObjectHasValueMatch1 getSubClassInclusionDecomposedObjectHasValueMatch1(
				SubClassInclusionDecomposedMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch,
				ElkObjectHasValue premiseSubsumerMatch);

	}

}
