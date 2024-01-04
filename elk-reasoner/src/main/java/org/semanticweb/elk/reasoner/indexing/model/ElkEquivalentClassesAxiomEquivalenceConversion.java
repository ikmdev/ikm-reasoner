 
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;

/**
 * Represents a transformation of an {@link ElkEquivalentClassesAxiom} to an
 * {@link IndexedEquivalentClassesAxiom} with two members representing the
 * corresponding members of the {@link ElkEquivalentClassesAxiom}.
 * 
 * @see ElkEquivalentClassesAxiom#getClassExpressions()
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkEquivalentClassesAxiomEquivalenceConversion
		extends IndexedEquivalentClassesAxiomInference {

	@Override
	ElkEquivalentClassesAxiom getOriginalAxiom();

	/**
	 * @return the position of the {@link ElkClassExpression} in the member list
	 *         of the {@link ElkEquivalentClassesAxiom} that is converted to the
	 *         first member of the resulting
	 *         {@link IndexedEquivalentClassesAxiom}
	 * 
	 * @see ElkEquivalentClassesAxiom#getClassExpressions()
	 * @see IndexedEquivalentClassesAxiom#getFirstMember()
	 */
	int getFirstMemberPosition();

	/**
	 * @return the position of the {@link ElkClassExpression} in the member list
	 *         of the {@link ElkEquivalentClassesAxiom} that is converted to the
	 *         second member of the resulting
	 *         {@link IndexedEquivalentClassesAxiom}
	 * 
	 * @see ElkEquivalentClassesAxiom#getClassExpressions()
	 * @see IndexedEquivalentClassesAxiom#getSecondMember()
	 */
	int getSecondMemberPosition();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkEquivalentClassesAxiomEquivalenceConversion inference);

	}

}
