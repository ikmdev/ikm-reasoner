
package org.semanticweb.elk.owl.interfaces;


import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityVisitor;

/**
 * A common interface for qualified and unqualified min cardinality restrictions
 * on data.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ElkDataExactCardinality
		extends ElkCardinalityRestriction<ElkDataPropertyExpression> {

	/**
	 * Accept an {@link ElkDataExactCardinalityVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataExactCardinalityVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataExactCardinalityQualified.Factory,
			ElkDataExactCardinalityUnqualified.Factory {

		// combined interface

	}

}
