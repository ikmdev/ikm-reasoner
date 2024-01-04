
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkDataPropertyExpressionVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Data_Property_Expressions">Data
 * Property Expression<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataPropertyExpression extends ElkObject {

	/**
	 * Accept an {@link ElkDataPropertyExpressionVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkDataPropertyExpressionVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataProperty.Factory {

		// combined interface

	}

}
