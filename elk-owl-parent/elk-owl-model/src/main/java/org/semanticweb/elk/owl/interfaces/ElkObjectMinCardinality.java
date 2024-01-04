
package org.semanticweb.elk.owl.interfaces;


import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityVisitor;

/**
 * A common interface for qualified and unqualified min cardinality restrictions
 * on objects.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ElkObjectMinCardinality
		extends ElkCardinalityRestriction<ElkObjectPropertyExpression> {

	/**
	 * Accept an {@link ElkObjectMinCardinalityVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectMinCardinalityVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkObjectMinCardinalityQualified.Factory,
			ElkObjectMinCardinalityUnqualified.Factory {

		// combined interface

	}

}
