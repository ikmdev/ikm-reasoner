
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * Represents a transformation of an {@link ElkDisjointUnionAxiom} to an
 * {@link IndexedSubClassOfAxiom} representing the disjointness of two members
 * of the union.
 *
 * @see ElkDisjointUnionAxiom#getClassExpressions()
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ElkDisjointUnionAxiomBinaryConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkDisjointUnionAxiom getOriginalAxiom();

	/**
	 * @return the position of the first {@link ElkClassExpression} in the
	 *         member list of the union of the {@link ElkDisjointUnionAxiom}
	 *         used in the binary disjointness represented by the
	 *         {@link IndexedSubClassOfAxiom}.
	 *
	 * @see ElkDisjointUnionAxiom#getClassExpressions()
	 * @see #getSecondDisjunctPosition()
	 */
	int getFirstDisjunctPosition();

	/**
	 * @return the position of the second {@link ElkClassExpression} in the
	 *         member list of the union of the {@link ElkDisjointUnionAxiom}
	 *         used in the binary disjointness represented by the
	 *         {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkDisjointUnionAxiom#getClassExpressions()
	 * @see #getFirstDisjunctPosition()
	 */
	int getSecondDisjunctPosition();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDisjointUnionAxiomBinaryConversion inference);

	}

}
