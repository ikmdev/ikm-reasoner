
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * An {@link IndexedAxiom} constructed from an
 * {@link IndexedClassExpressionList}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * [Disjoint(L)]
 * </pre>
 * 
 * It is logically equivalent to the OWL axiom {@code DisjointClasses(L)} <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * L = {@link #getMembers()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedDisjointClassesAxiom extends IndexedAxiom {

	/**
	 * @return the {@link IndexedClassExpressionList} representing the members
	 *         of the {@link ElkDisjointClassesAxiom} represented by this
	 *         {@link IndexedDisjointClassesAxiom}
	 * 
	 * @see ElkDisjointClassesAxiom#getClassExpressions()
	 */
	IndexedClassExpressionList getMembers();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		IndexedDisjointClassesAxiom getIndexedDisjointClassesAxiom(
				ElkAxiom originalAxiom, IndexedClassExpressionList members);

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

		O visit(IndexedDisjointClassesAxiom axiom);

	}

}
