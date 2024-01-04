
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link ModifiableIndexedObject} that occurs inside axioms.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedSubObject extends ModifiableIndexedObject {

	/**
	 * Tries to change the number of occurrences of this
	 * {@link ModifiableIndexedSubObject} in the given
	 * {@link ModifiableOntologyIndex} according to the given
	 * {@link OccurrenceIncrement}
	 * 
	 * @param index
	 *            the {@link ModifiableOntologyIndex} representing the logical
	 *            structure of the ontology
	 * 
	 * @param increment
	 *            how many occurrences should be added (if positive) or removed
	 *            (if negative)
	 * 
	 * @return {@code true} if the operation has been successful or
	 *         {@code false} otherwise. If {@code false} is return, the provided
	 *         {@link ModifiableOntologyIndex} should not logically change,
	 *         i.e., it should correspond to the same logical representation of
	 *         the ontology as before the operation
	 */
	boolean updateOccurrenceNumbers(ModifiableOntologyIndex index,
			OccurrenceIncrement increment);

	interface Factory
			extends
				ModifiableIndexedClassExpression.Factory,
				ModifiableIndexedClassExpressionList.Factory,
				ModifiableIndexedEntity.Factory,
				ModifiableIndexedPropertyChain.Factory {

		// combined interface

	}
	
}
