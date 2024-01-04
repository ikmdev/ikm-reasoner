
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkObjectOneOfVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Enumeration_of_Individuals">
 * Enumeration of Individuals<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkObjectOneOf extends ElkClassExpression {

	/**
	 * Get the list of individuals that this expression refers to. The order of
	 * individuals does not affect the semantics but it is relevant to the
	 * syntax of OWL.
	 * 
	 * @return list of individuals
	 */
	public List<? extends ElkIndividual> getIndividuals();

	/**
	 * Accept an {@link ElkObjectOneOfVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkObjectOneOfVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkObjectOneOf}.
		 * 
		 * @param first
		 *            the first {@link ElkIndividual} for which the object
		 *            should be created
		 * @param other
		 *            other {@link ElkIndividual}s for which the object should
		 *            be created
		 * @return an {@link ElkObjectOneOf} corresponding to the input
		 */
		public ElkObjectOneOf getObjectOneOf(ElkIndividual first,
				ElkIndividual... other);

		/**
		 * Create an {@link ElkObjectOneOf}.
		 * 
		 * @param members
		 *            the {@link ElkIndividual}s for which the object should be
		 *            created
		 * @return an {@link ElkObjectOneOf} corresponding to the input
		 */
		public ElkObjectOneOf getObjectOneOf(
				List<? extends ElkIndividual> members);

	}

}
