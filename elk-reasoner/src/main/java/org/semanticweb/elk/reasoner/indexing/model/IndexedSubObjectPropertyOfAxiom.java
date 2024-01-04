
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;

/**
 * An {@link IndexedAxiom} constructed from an {@link IndexedPropertyChain} and
 * an {@link IndexedObjectProperty}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * [P ⊑ R]
 * </pre>
 * 
 * It is logically equivalent to the OWL axiom {@code SubObjectPropertyOf(P R)}
 * <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * P = {@link #getSubPropertyChain()}<br>
 * R = {@link #getSuperProperty()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedSubObjectPropertyOfAxiom extends IndexedAxiom {

	/**
	 * @return the {@link IndexedPropertyChain} representing the sub property
	 *         expression of the {@link ElkSubObjectPropertyOfAxiom} represented
	 *         by this {@link IndexedSubObjectPropertyOfAxiom}
	 * 
	 * @see ElkSubObjectPropertyOfAxiom#getSubObjectPropertyExpression()
	 */
	IndexedPropertyChain getSubPropertyChain();

	/**
	 * @return the {@link IndexedObjectProperty} representing the super property
	 *         of the {@link ElkSubObjectPropertyOfAxiom} represented by this
	 *         {@link IndexedSubObjectPropertyOfAxiom}
	 * 
	 * @see ElkSubObjectPropertyOfAxiom#getSuperObjectPropertyExpression()
	 */
	IndexedObjectProperty getSuperProperty();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		IndexedSubObjectPropertyOfAxiom getIndexedSubObjectPropertyOfAxiom(
				ElkAxiom originalAxiom, IndexedPropertyChain subPropertyChain,
				IndexedObjectProperty superProperty);

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

		O visit(IndexedSubObjectPropertyOfAxiom axiom);

	}

}
