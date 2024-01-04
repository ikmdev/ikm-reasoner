
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedRangeFiller;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.PropertyRange;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionDecomposed} from
 * {@link ContextInitialization} and {@link PropertyRange}:<br>
 * 
 * <pre>
 *    (1)      (2)
 *  !([C])  Range(R,D)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ where C = (∃R^-.T) ⊓ C'
 *       [C] ⊑ -D
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()}<br>
 * D = {@link #getRange() = #getConclusionSubsumer()}
 * 
 * @author Yevgeny Kazakov
 */
public class SubClassInclusionRange
		extends AbstractSubClassInclusionDecomposedInference {

	public SubClassInclusionRange(IndexedRangeFiller inferenceRoot,
			IndexedClassExpression propertyRange) {
		super(inferenceRoot, propertyRange);
	}

	@Override
	public IndexedRangeFiller getOrigin() {
		return (IndexedRangeFiller) getDestination();
	}

	public IndexedClassExpression getRange() {
		return getSubsumer();
	}

	public ContextInitialization getFirstPremise(
			ContextInitialization.Factory factory) {
		return factory.getContextInitialization(getOrigin());
	}

	public PropertyRange getSecondPremise(PropertyRange.Factory factory) {
		return factory.getPropertyRange(getOrigin().getProperty(),
				getSubsumer());
	}

	@Override
	public int getPremiseCount() {
		return 2;
	}

	@Override
	public Conclusion getPremise(int index, Factory factory) {
		switch (index) {
		case 0:
			return getFirstPremise(factory);
		case 1:
			return getSecondPremise(factory);
		default:
			return failGetPremise(index);
		}
	}

	@Override
	public final <O> O accept(
			SubClassInclusionDecomposedInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O> {

		public O visit(SubClassInclusionRange inference);

	}

}
