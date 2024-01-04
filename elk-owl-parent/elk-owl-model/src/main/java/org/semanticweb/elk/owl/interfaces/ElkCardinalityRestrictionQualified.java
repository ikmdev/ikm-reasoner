
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;

/**
 * A generic interface for restrictions on properties with fillers (qualifiers)
 * and cardinality constraints.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this restriction
 * @param <F>
 *            the type of the filler of this restriction
 */
public interface ElkCardinalityRestrictionQualified<P, F> extends
		ElkCardinalityRestriction<P>, ElkPropertyRestrictionQualified<P, F> {

	/**
	 * Accept an {@link ElkCardinalityRestrictionQualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataExactCardinalityQualified.Factory,
			ElkDataMaxCardinalityQualified.Factory,
			ElkDataMinCardinalityQualified.Factory,
			ElkObjectExactCardinalityQualified.Factory,
			ElkObjectMaxCardinalityQualified.Factory,
			ElkObjectMinCardinalityQualified.Factory {

		// combined interface

	}

}
