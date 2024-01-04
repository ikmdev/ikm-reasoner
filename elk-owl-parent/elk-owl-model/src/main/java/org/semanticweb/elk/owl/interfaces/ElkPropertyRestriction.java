 
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;

/**
 * A generic interface for class expressions with data properties or object
 * properties.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this expression
 */
public interface ElkPropertyRestriction<P> extends ElkClassExpression {

	/**
	 * Get the property of this restriction.
	 * 
	 * @return the property of this restriction
	 */
	P getProperty();

	/**
	 * Accept an {@link ElkPropertyRestrictionVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkCardinalityRestriction.Factory,
			ElkObjectHasSelf.Factory, ElkPropertyRestrictionQualified.Factory {

		// combined interface

	}

}
