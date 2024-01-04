
package org.semanticweb.elk.owl.interfaces;


import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityVisitor;

/**
 * A common interface for qualified and unqualified min cardinality restrictions
 * on data.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ElkDataMinCardinality
		extends ElkCardinalityRestriction<ElkDataPropertyExpression> {

	/**
	 * Accept an {@link ElkDataMinCardinalityVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataMinCardinalityVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataMinCardinalityQualified.Factory,
			ElkDataMinCardinalityUnqualified.Factory {

		// combined interface

	}

}
