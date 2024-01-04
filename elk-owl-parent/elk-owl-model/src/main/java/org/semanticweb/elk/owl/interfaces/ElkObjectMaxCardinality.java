
package org.semanticweb.elk.owl.interfaces;


import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityVisitor;

/**
 * A common interface for qualified and unqualified max cardinality restrictions
 * on objects.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ElkObjectMaxCardinality
		extends ElkCardinalityRestriction<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkObjectMaxCardinalityVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectMaxCardinalityVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkObjectMaxCardinalityQualified.Factory,
			ElkObjectMaxCardinalityUnqualified.Factory {

		// combined interface

	}

}
