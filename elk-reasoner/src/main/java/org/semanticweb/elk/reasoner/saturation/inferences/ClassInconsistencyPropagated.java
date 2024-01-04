
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion.Factory;

/**
 * A {@link ClassInference} producing a {@link ClassInconsistency} from a
 * {@link BackwardLink} and another {@link ClassInconsistency}:<br>
 * 
 * <pre>
 *   (1)             (2)
 *  C ⊑ <∃R>.[D]  [D] ⊑ 0  
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *        [C] ⊑ 0
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * R = {@link #getPremiseRelation()}<br>
 * D = {@link #getOrigin()}<br>
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @author Yevgeny Kazakov
 */
public class ClassInconsistencyPropagated
		extends AbstractClassInconsistencyInference {

	private final IndexedContextRoot origin_;

	private final IndexedObjectProperty premiseRelation_;

	public ClassInconsistencyPropagated(IndexedContextRoot origin,
			IndexedObjectProperty relation, IndexedContextRoot destination) {
		super(destination);
		premiseRelation_ = relation;
		origin_ = origin;
	}

	public ClassInconsistencyPropagated(BackwardLink premise) {
		this(premise.getDestination(), premise.getRelation(),
				premise.getTraceRoot());
	}

	@Override
	public IndexedContextRoot getOrigin() {
		return origin_;
	}

	public IndexedObjectProperty getPremiseRelation() {
		return premiseRelation_;
	}

	public BackwardLink getFirstPremise(BackwardLink.Factory factory) {
		return factory.getBackwardLink(getOrigin(), premiseRelation_,
				getDestination());
	}

	public ClassInconsistency getSecondPremise(
			ClassInconsistency.Factory factory) {
		return factory.getContradiction(getOrigin());
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

		public O visit(ClassInconsistencyPropagated inference);

	}

}
