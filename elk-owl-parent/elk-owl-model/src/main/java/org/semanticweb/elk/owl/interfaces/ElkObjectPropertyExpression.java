
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Property_Expressions">
 * Object Property Expression<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectPropertyExpression
		extends ElkSubObjectPropertyExpression, ElkObject {

	/**
	 * Accept an {@link ElkObjectPropertyExpressionVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkObjectPropertyExpressionVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ElkObjectInverseOf.Factory, ElkObjectProperty.Factory {

		// combined interface

	}

}
