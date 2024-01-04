
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * Represents a transformation of an {@link ElkDisjointClassesAxiom} to an
 * {@link IndexedSubClassOfAxiom} representing that two selected
 * {@link ElkClassExpression}s are disjoint.
 * 
 * @see ElkDisjointClassesAxiom#getClassExpressions()
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ElkDisjointClassesAxiomBinaryConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkDisjointClassesAxiom getOriginalAxiom();
	
	/**
	 * @return the position of the first {@link ElkClassExpression} in the
	 *         member list of the {@link ElkDisjointClassesAxiom} that is stated
	 *         to be disjoint with the second one using the
	 *         {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkDisjointClassesAxiom#getClassExpressions()
	 * @see #getSecondClassPosition()
	 */
	int getFirstClassPosition();

	/**
	 * @return the position of the second {@link ElkClassExpression} in the
	 *         member list of the {@link ElkDisjointClassesAxiom} that is stated
	 *         to be disjoint with the first one using the
	 *         {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkDisjointClassesAxiom#getClassExpressions()
	 * @see #getFirstClassPosition()
	 */
	int getSecondClassPosition();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDisjointClassesAxiomBinaryConversion inference);

	}

}
