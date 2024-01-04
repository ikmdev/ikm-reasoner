
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Individuals">Individual<a> in the
 * OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkIndividual extends ElkObject {

	/**
	 * Accept an {@link ElkIndividualVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public abstract <O> O accept(ElkIndividualVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ElkAnonymousIndividual.Factory, ElkNamedIndividual.Factory {

		// combined interface

	}

}
