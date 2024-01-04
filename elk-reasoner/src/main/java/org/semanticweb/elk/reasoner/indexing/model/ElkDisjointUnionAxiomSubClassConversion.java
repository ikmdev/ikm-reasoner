
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * Represents a transformation of an {@link ElkDisjointUnionAxiom} to an
 * {@link IndexedSubClassOfAxiom} representing the inclusion between a selected
 * member of the union and the defined class of the
 * {@link ElkDisjointUnionAxiom}.
 * 
 * @see ElkDisjointUnionAxiom#getClassExpressions()
 * @see ElkDisjointUnionAxiom#getDefinedClass()
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkDisjointUnionAxiomSubClassConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkDisjointUnionAxiom getOriginalAxiom();

	/**
	 * @return the position of an {@link ElkClassExpression} in the union of the
	 *         {@link ElkDisjointUnionAxiom} that is converted to the sub-class
	 *         of the {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkDisjointUnionAxiom#getClassExpressions()
	 * @see IndexedSubClassOfAxiom#getSubClass()
	 */
	int getDisjunctPosition();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDisjointUnionAxiomSubClassConversion inference);

	}

}
