
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



/**
 * An {@link IndexedAxiom} constructed from two {@link IndexedClassExpression}s.
 * <br>
 * 
 * Notation:
 * 
 * <pre>
 * [C = D]
 * </pre>
 * 
 * It is logically equivalent to the OWL axiom {@code EquivalentClasses(C D)}
 * <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getFirstMember()}<br>
 * D = {@link #getSecondMember()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedEquivalentClassesAxiom extends IndexedAxiom {

	/**
	 * @return the first of the two equivalent {@link IndexedClassExpression}s
	 */
	IndexedClassExpression getFirstMember();

	/**
	 * @return the first of the two equivalent {@link IndexedClassExpression}s
	 */
	IndexedClassExpression getSecondMember();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedEquivalentClassesAxiom axiom);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		IndexedEquivalentClassesAxiom getIndexedEquivalentClassesAxiom(
				ElkAxiom originalAxiom, IndexedClassExpression firstMember,
				IndexedClassExpression secondMember);

	}

}
