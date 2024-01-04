
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkAnonymousIndividualVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Anonymous_Individuals">Anonymous
 * Individuals<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkAnonymousIndividual extends ElkIndividual,
		ElkAnnotationValue, ElkAnnotationSubject {

	/**
	 * Get the nodeID of this anonymous individual.
	 * 
	 * @return The nodeID of anonymous individual.
	 */
	public abstract String getNodeId();

	/**
	 * Accept an {@link ElkAnonymousIndividualVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnonymousIndividualVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {
		
		/**
		 * Create an {@link ElkAnonymousIndividual}.
		 * 
		 * @param nodeId
		 *            the {@link String} for which the object should be created
		 * @return an {@link ElkAnonymousIndividual} corresponding to the input
		 */
		public ElkAnonymousIndividual getAnonymousIndividual(String nodeId);

	}

}
