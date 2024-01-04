
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionDecomposed;

/**
 * A {@link ClassInference} producing a {@link SubClassInclusionDecomposed} from
 * a {@link SubClassInclusionDecomposed} with
 * {@link SubClassInclusionComposed#getSubsumer()} instance of
 * {@link IndexedObjectIntersectionOf}:<br>
 * 
 * <pre>
 *  [C] ⊑ -(D1 ⊓ D2)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *      [C] ⊑ -D1
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getOrigin()} = {@link #getDestination()}<br>
 * D1 ⊓ D2 = {@link #getConjunction()} (from which D1 can be obtained by
 * {@link IndexedObjectIntersectionOf#getFirstConjunct()})<br>
 * 
 * @see IndexedObjectIntersectionOf#getFirstConjunct()
 * 
 * @author "Yevgeny Kazakov"
 */
public class SubClassInclusionDecomposedFirstConjunct
		extends SubClassInclusionDecomposedConjunct {

	public SubClassInclusionDecomposedFirstConjunct(IndexedContextRoot root,
			IndexedObjectIntersectionOf subsumer) {
		super(root, subsumer, subsumer.getFirstConjunct());
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

		public O visit(SubClassInclusionDecomposedFirstConjunct inference);

	}

}
