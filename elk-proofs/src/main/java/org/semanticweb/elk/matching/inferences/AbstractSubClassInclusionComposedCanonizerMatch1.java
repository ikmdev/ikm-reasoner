
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public abstract class AbstractSubClassInclusionComposedCanonizerMatch1
		extends AbstractInferenceMatch<SubClassInclusionComposed> {

	private final IndexedContextRootMatch destinationMatch_;

	AbstractSubClassInclusionComposedCanonizerMatch1(
			SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch) {
		super(parent);
		this.destinationMatch_ = destinationMatch;
	}

	IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
	}

	public abstract SubClassInclusionComposedMatch1 getPremiseMatch(
			ConclusionMatchExpressionFactory factory);

	abstract SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory);

}
