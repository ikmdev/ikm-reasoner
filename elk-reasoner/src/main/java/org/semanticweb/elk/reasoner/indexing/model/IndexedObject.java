
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * Represents occurrences of an {@link ElkObject} in an ontology.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface IndexedObject {

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
				IndexedClassExpression.Visitor<O>,
				IndexedPropertyChain.Visitor<O>,
				IndexedAxiom.Visitor<O>,
				IndexedEntity.Visitor<O>,
				IndexedClassExpressionList.Visitor<O>,
				IndexedContextRoot.Visitor<O> {

		// combined interface

	}
	
	<O> O accept(Visitor<O> visitor);

}
