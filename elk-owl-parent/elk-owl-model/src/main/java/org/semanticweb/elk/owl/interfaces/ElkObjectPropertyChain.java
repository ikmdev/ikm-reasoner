
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkObjectPropertyChainVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Object_Subproperties">object
 * property chains<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectPropertyChain extends ElkSubObjectPropertyExpression {

	/**
	 * Get the list of object property expressions that this expression refers
	 * to. The order of object property expressions is important for the syntax
	 * and semantics of OWL. The list should have at east two members.
	 * 
	 * @return list of object property expressions
	 */
	public List<? extends ElkObjectPropertyExpression> getObjectPropertyExpressions();

	/**
	 * Accept an {@link ElkObjectPropertyChainVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectPropertyChainVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectPropertyChain}.
		 * 
		 * @param properties
		 *            the {@link ElkObjectPropertyExpression}s for which the
		 *            object should be created
		 * @return an {@link ElkObjectPropertyChain} corresponding to the input
		 */
		public ElkObjectPropertyChain getObjectPropertyChain(
				List<? extends ElkObjectPropertyExpression> properties);
	}

}
