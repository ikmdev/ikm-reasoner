
package org.semanticweb.elk.owl.interfaces;


import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityVisitor;

/**
 * A common interface for qualified and unqualified min cardinality restrictions
 * on data.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ElkDataMaxCardinality
		extends ElkCardinalityRestriction<ElkDataPropertyExpression> {

	/**
	 * Accept an {@link ElkDataMaxCardinalityVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataMaxCardinalityVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataMaxCardinalityQualified.Factory,
			ElkDataMaxCardinalityUnqualified.Factory {

		// combined interface

	}

}
