
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Represents all occurrences of an {@link ElkEntity} in an ontology.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface IndexedEntity extends IndexedObject {

	/**
	 * @return The {@link ElkEntity} represented by this {@link IndexedEntity}
	 */
	ElkEntity getElkEntity();

	/**
	 * @return {@code true} if this {@link IndexedClassExpression} occurs in the
	 *         ontology
	 */
	boolean occurs();

	String printOccurrenceNumbers();
	
	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends
				IndexedClassEntity.Visitor<O>,
				IndexedObjectProperty.Visitor<O> {

		// combined interface

	}
	
	<O> O accept(IndexedEntity.Visitor<O> visitor);

}
