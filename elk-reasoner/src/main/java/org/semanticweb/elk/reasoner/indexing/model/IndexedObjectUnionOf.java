
package org.semanticweb.elk.reasoner.indexing.model;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;

/**
 * An {@link IndexedClassExpression} constructed from a list of
 * {@link IndexedClassExpression}s.<br>
 * 
 * Notation:
 * 
 * <pre>
 * C1 ⊔ C2 ⊔ ... ⊔ Cn
 * </pre>
 * 
 * It is logically equivalent to OWL class expression
 * {@code ObjectUnionOf(C1 C2 ... Cn)} <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * Ci = the i-th position of {@link #getDisjuncts()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedObjectUnionOf extends IndexedClassExpression {

	/**
	 * @return the {@link IndexedClassExpression}s representing the disjuncts of
	 *         the {@link ElkObjectUnionOf} represented by this
	 *         {@link IndexedObjectUnionOf}.
	 * 
	 * @see IndexedObjectUnionOf#getDisjuncts()
	 */
	List<? extends IndexedClassExpression> getDisjuncts();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedObjectUnionOf element);

	}

}
