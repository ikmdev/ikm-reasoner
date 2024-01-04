
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * An {@link IndexedAxiom} constructed from two {@link IndexedClassExpression}s.
 * <br>
 * 
 * Notation:
 * 
 * <pre>
 * [C ⊑ D]
 * </pre>
 * 
 * It is logically equivalent to the OWL axiom {@code SubClassOf(C D)}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getSubClass()}<br>
 * R = {@link #getSuperClass()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedSubClassOfAxiom extends IndexedAxiom {

	/**
	 * @return the {@link IndexedClassExpression} representing the sub class of
	 *         the {@link ElkSubClassOfAxiom} represented by this
	 *         {@link IndexedSubClassOfAxiom}
	 * 
	 * @see IndexedSubClassOfAxiom#getSubClass()
	 */
	IndexedClassExpression getSubClass();

	/**
	 * @return the {@link IndexedClassExpression} representing the super class
	 *         of the {@link ElkSubClassOfAxiom} represented by this
	 *         {@link IndexedSubClassOfAxiom}
	 * 
	 * @see IndexedSubClassOfAxiom#getSuperClass()
	 */
	IndexedClassExpression getSuperClass();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		public IndexedSubClassOfAxiom getIndexedSubClassOfAxiom(
				ElkAxiom originalAxiom, IndexedClassExpression subClass,
				IndexedClassExpression superClass);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedSubClassOfAxiom axiom);

	}

}
