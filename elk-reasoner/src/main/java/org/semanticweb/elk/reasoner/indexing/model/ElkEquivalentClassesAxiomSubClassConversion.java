
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;

/**
 * Represents a transformation of an {@link ElkEquivalentClassesAxiom} to an
 * {@link IndexedSubClassOfAxiom} representing the inclusion between two
 * selected members of the {@link ElkEquivalentClassesAxiom}.
 * 
 * @see ElkEquivalentClassesAxiom#getClassExpressions()
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkEquivalentClassesAxiomSubClassConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkEquivalentClassesAxiom getOriginalAxiom();

	/**
	 * @return the position of an {@link ElkClassExpression} in the member list
	 *         of the {@link ElkEquivalentClassesAxiom} that is converted to the
	 *         sub-class of the {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkEquivalentClassesAxiom#getClassExpressions()
	 * @see IndexedSubClassOfAxiom#getSubClass()
	 */
	int getSubClassPosition();

	/**
	 * @return the position of an {@link ElkClassExpression} in the member list
	 *         of the {@link ElkEquivalentClassesAxiom} that is converted to the
	 *         super-class of the {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkEquivalentClassesAxiom#getClassExpressions()
	 * @see IndexedSubClassOfAxiom#getSuperClass()
	 */
	int getSuperClassPosition();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkEquivalentClassesAxiomSubClassConversion inference);

	}

}
