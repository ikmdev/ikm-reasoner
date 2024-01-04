
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public abstract class AbstractSubClassInclusionDecomposedCanonizerMatch1
		extends AbstractInferenceMatch<SubClassInclusionDecomposedMatch1> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	AbstractSubClassInclusionDecomposedCanonizerMatch1(
			SubClassInclusionDecomposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch) {
		super(parent);
		this.extendedDestinationMatch_ = extendedDestinationMatch;
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	abstract SubClassInclusionDecomposedMatch2 getPremiseMatch(
			ConclusionMatchExpressionFactory factory);

	public abstract SubClassInclusionDecomposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory);

}
