
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionComposed} with
 * {@link SubClassInclusionComposed#getSubsumer()} instance of
 * {@link IndexedObjectIntersectionOf} from two
 * {@link SubClassInclusionComposed} premises:<br>
 * 
 * <pre>
 *     (1)        (2)
 *  [C] ⊑ +D1  [C] ⊑ +D2
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *    [C] ⊑ +(D1 ⊓ D2)
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()}<br>
 * D1 ⊓ D2 = {@link #getConclusionSubsumer()} (from which D1 and D2 can be
 * obtained)<br>
 * 
 * @see IndexedObjectIntersectionOf#getFirstConjunct()
 * @see IndexedObjectIntersectionOf#getSecondConjunct()
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author Yevgeny Kazakov
 * 
 */
public class SubClassInclusionComposedObjectIntersectionOf extends
		AbstractSubClassInclusionComposedInference<IndexedObjectIntersectionOf> {

	public SubClassInclusionComposedObjectIntersectionOf(
			IndexedContextRoot inferenceRoot,
			IndexedObjectIntersectionOf conjunction) {
		super(inferenceRoot, conjunction);
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return getDestination();
	}

	public SubClassInclusionComposed getFirstPremise(
			SubClassInclusionComposed.Factory factory) {
		return factory.getSubClassInclusionComposed(getOrigin(),
				getSubsumer().getFirstConjunct());
	}

	public SubClassInclusionComposed getSecondPremise(
			SubClassInclusionComposed.Factory factory) {
		return factory.getSubClassInclusionComposed(getOrigin(),
				getSubsumer().getSecondConjunct());
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
			SubClassInclusionComposedInference.Visitor<O> visitor) {
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

		public O visit(SubClassInclusionComposedObjectIntersectionOf inference);

	}

}
