
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectInverseOfVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Inverse_Object_Properties">
 * Inverse Object Property<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectInverseOf extends ElkObjectPropertyExpression {

	/**
	 * Get the object property expression that this expression refers to.
	 * 
	 * @return object property expression
	 */
	public ElkObjectProperty getObjectProperty();

	/**
	 * Accept an {@link ElkObjectInverseOfVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectInverseOfVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectInverseOf}.
		 * 
		 * @param property
		 *            the {@link ElkObjectProperty} for which the object should
		 *            be created
		 * @return an {@link ElkObjectInverseOf} corresponding to the input
		 */
		public ElkObjectInverseOf getObjectInverseOf(
				ElkObjectProperty property);
	}

}
