
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;

/**
 * A generic interface on property restrictions with cardinality constraints.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 */
public interface ElkCardinalityRestriction<P>
		extends ElkPropertyRestriction<P> {

	/**
	 * @return the cardinality that this expression refers to.
	 */
	int getCardinality();

	/**
	 * Accept an {@link ElkCardinalityRestrictionVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkCardinalityRestrictionQualified.Factory,
			ElkDataExactCardinality.Factory, ElkDataMaxCardinality.Factory,
			ElkDataMinCardinality.Factory, ElkObjectExactCardinality.Factory,
			ElkObjectMaxCardinality.Factory, ElkObjectMinCardinality.Factory {

		// combined interface

	}

}
