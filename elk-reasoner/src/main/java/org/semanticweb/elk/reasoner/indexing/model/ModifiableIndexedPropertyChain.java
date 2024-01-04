
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * An {@link IndexedPropertyChain} that can be modified as a result of updating
 * the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedPropertyChain extends
		ModifiableIndexedSubObject, IndexedPropertyChain,
		Comparable<ModifiableIndexedPropertyChain> {

	/**
	 * Adds the given {@link IndexedObjectProperty} as a super-role of this
	 * {@link IndexedPropertyChain}
	 * 
	 * @param superObjectProperty
	 *            the {@link IndexedObjectProperty} to be added
	 * @param reason
	 *            the {@link ElkAxiom} responsible for the super-property
	 * @return {@code true} if the operation is successful or {@code false}
	 *         otherwise; if {@code false} is returned, this
	 *         {@link IndexedPropertyChain} does not change
	 */
	boolean addToldSuperObjectProperty(
			IndexedObjectProperty superObjectProperty, ElkAxiom reason);

	/**
	 * Removes the given {@link IndexedObjectProperty} from super-roles of this
	 * {@link IndexedPropertyChain}
	 * 
	 * @param superObjectProperty
	 *            the {@link IndexedObjectProperty} to be removed
	 * @param reason
	 *            the {@link ElkAxiom} responsible for the super-property
	 * @return {@code true} if the operation is successful or {@code false}
	 *         otherwise; if {@code false} is returned, this
	 *         {@link IndexedPropertyChain} does not change
	 */
	boolean removeToldSuperObjectProperty(
			IndexedObjectProperty superObjectProperty, ElkAxiom reason);

	/**
	 * Adds the given {@link IndexedComplexPropertyChain} to the list of
	 * {@link IndexedComplexPropertyChain} that contains this
	 * {@link IndexedPropertyChain} in the right-hand-side
	 * 
	 * @param chain
	 *            the {@link IndexedComplexPropertyChain} to be added
	 * @return {@code true} if the operation is successful or {@code false}
	 *         otherwise; if {@code false} is returned, this
	 *         {@link IndexedPropertyChain} does not change
	 */
	boolean addRightChain(IndexedComplexPropertyChain chain);

	/**
	 * Adds the given {@link IndexedComplexPropertyChain} from the list of
	 * {@link IndexedComplexPropertyChain} that contain this
	 * {@link IndexedPropertyChain} in the right-hand-side
	 * 
	 * @param chain
	 *            the {@link IndexedComplexPropertyChain} to be removed
	 * @return {@code true} if the operation is successful or {@code false}
	 *         otherwise; if {@code false} is returned, this
	 *         {@link IndexedPropertyChain} does not change
	 */
	boolean removeRightChain(IndexedComplexPropertyChain chain);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ModifiableIndexedComplexPropertyChain.Factory,
				ModifiableIndexedObjectProperty.Factory {

		// combined interface

	}

}
