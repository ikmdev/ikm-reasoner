
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectComplementOf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link ClassInconsistency} obtained from
 * {@link SubClassInclusionComposed} and {@link SubClassInclusionDecomposed}
 * such that {@link SubClassInclusionDecomposed#getSubsumer()} is the negation
 * of the {@link SubClassInclusionComposed#getSubsumer()}:<br>
 * 
 * <pre>
 *     (1)       (2)
 *  [C] ⊑ +D  [C] ⊑ -¬D
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *        [C] ⊑ 0
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()}={@link #getDestination()}<br>
 * ¬D = {@link #getNegation()} (from which D can be obtained)<br>
 * 
 * @see IndexedObjectComplementOf#getNegated()
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 */
public class ClassInconsistencyOfObjectComplementOf
		extends AbstractClassInconsistencyInference {

	private final IndexedObjectComplementOf negation_;

	public ClassInconsistencyOfObjectComplementOf(IndexedContextRoot root,
			IndexedObjectComplementOf negatedSubsumer) {
		super(root);
		this.negation_ = negatedSubsumer;
	}

	public IndexedObjectComplementOf getNegation() {
		return negation_;
	}

	public SubClassInclusionComposed getFirstPremise(
			SubClassInclusionComposed.Factory factory) {
		return factory.getSubClassInclusionComposed(getOrigin(),
				negation_.getNegated());
	}

	public SubClassInclusionDecomposed getSecondPremise(
			SubClassInclusionDecomposed.Factory factory) {
		return factory.getSubClassInclusionDecomposed(getOrigin(), negation_);
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
	public final <O> O accept(ClassInconsistencyInference.Visitor<O> visitor) {
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

		public O visit(ClassInconsistencyOfObjectComplementOf inference);

	}

}
