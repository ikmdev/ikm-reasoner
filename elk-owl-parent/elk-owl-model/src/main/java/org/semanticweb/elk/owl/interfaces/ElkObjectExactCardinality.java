
package org.semanticweb.elk.owl.interfaces;


import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityVisitor;

/**
 * A common interface for qualified and unqualified min cardinality restrictions
 * on objects.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ElkObjectExactCardinality
		extends ElkCardinalityRestriction<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkObjectExactCardinalityVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectExactCardinalityVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkObjectExactCardinalityQualified.Factory,
			ElkObjectExactCardinalityUnqualified.Factory {

		// combined interface

	}
}
