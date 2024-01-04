
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Subproperties">sub object
 * property expressions<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkSubObjectPropertyExpression extends ElkObject {

	/**
	 * Accept an {@link ElkSubObjectPropertyExpressionVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(
			ElkSubObjectPropertyExpressionVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkObjectPropertyChain.Factory,
			ElkObjectPropertyExpression.Factory {

		// combined interface

	}

}
