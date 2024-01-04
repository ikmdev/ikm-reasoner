
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkNamedIndividualVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Named_Individuals">Named
 * Individuals<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkNamedIndividual extends ElkIndividual, ElkEntity {

	/**
	 * Accept an {@link ElkNamedIndividualVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkNamedIndividualVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkNamedIndividual}.
		 * 
		 * @param iri
		 *            the {@link ElkIri} for which the object should be created
		 * @return an {@link ElkNamedIndividual} corresponding to the input
		 */
		public ElkNamedIndividual getNamedIndividual(ElkIri iri);

	}

}
