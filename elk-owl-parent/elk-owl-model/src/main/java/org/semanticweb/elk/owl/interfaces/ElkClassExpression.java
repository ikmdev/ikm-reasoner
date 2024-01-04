
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;

/**
 * Corresponds to a
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Class_Expressions">Class
 * Expression<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * 
 */
public interface ElkClassExpression extends ElkObject {

	/**
	 * Accept an {@link ElkClassExpressionVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkClassExpressionVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkClass.Factory,
			ElkDataPropertyListRestrictionQualified.Factory,
			ElkObjectComplementOf.Factory, ElkObjectIntersectionOf.Factory,
			ElkObjectOneOf.Factory, ElkObjectUnionOf.Factory,
			ElkPropertyRestriction.Factory {

		// combined interface

	}

}
