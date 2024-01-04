
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedAxiom} that can be modified as a result of updating the
 * {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedAxiom
		extends ModifiableIndexedObject, IndexedAxiom {

	/**
	 * Adds this {@link ModifiableIndexedAxiom} once to the given
	 * {@link ModifiableOntologyIndex}
	 * 
	 * @param index
	 *            the {@link ModifiableOntologyIndex} to which this
	 *            {@link ModifiableIndexedAxiom} should be added
	 * 
	 * @return {@code true} if this operation was successful and {@code false}
	 *         otherwise; if {@code false} is returned, the index should not
	 *         logically change as the result of calling this method
	 */
	boolean addOccurrence(ModifiableOntologyIndex index);

	/**
	 * Removes this {@link ModifiableIndexedAxiom} once from the given
	 * {@link ModifiableOntologyIndex}
	 * 
	 * @param index
	 *            the {@link ModifiableOntologyIndex} from which this
	 *            {@link ModifiableIndexedAxiom} should be removed
	 * 
	 * @return {@code true} if this operation was successful and {@code false}
	 *         otherwise; if {@code false} is returned, the index should not
	 *         logically change as the result of calling this method
	 */
	boolean removeOccurrence(ModifiableOntologyIndex index);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ModifiableIndexedDisjointClassesAxiom.Factory,
			ModifiableIndexedSubClassOfAxiom.Factory,
			ModifiableIndexedEquivalentClassesAxiom.Factory,
			ModifiableIndexedSubObjectPropertyOfAxiom.Factory,
			ModifiableIndexedObjectPropertyRangeAxiom.Factory,
			ModifiableIndexedDeclarationAxiom.Factory {

		// combined interface

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends ModifiableIndexedDisjointClassesAxiom.Visitor<O>,
			ModifiableIndexedSubClassOfAxiom.Visitor<O>,
			ModifiableIndexedEquivalentClassesAxiom.Visitor<O>,
			ModifiableIndexedSubObjectPropertyOfAxiom.Visitor<O>,
			ModifiableIndexedObjectPropertyRangeAxiom.Visitor<O>,
			ModifiableIndexedDeclarationAxiom.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
