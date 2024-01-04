
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkDifferentIndividualsAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Individual_Inequality">individual
 * inequality axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDifferentIndividualsAxiom extends ElkAssertionAxiom {

	/**
	 * Get the list of individuals that this axiom refers to. The order of
	 * individuals does not affect the semantics but it is relevant to the
	 * syntax of OWL.
	 * 
	 * @return list of individuals
	 */
	public List<? extends ElkIndividual> getIndividuals();

	/**
	 * Accept an {@link ElkDifferentIndividualsAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDifferentIndividualsAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDifferentIndividualsAxiom}.
		 * 
		 * @param first
		 *            the first {@link ElkIndividual} for which the axiom should
		 *            be created
		 * @param second
		 *            the second {@link ElkIndividual} for which the axiom
		 *            should be created
		 * @param other
		 *            other {@link ElkIndividual} for which the axiom should be
		 *            created
		 * @return an {@link ElkAnnotation} corresponding to the input
		 */
		public ElkDifferentIndividualsAxiom getDifferentIndividualsAxiom(
				ElkIndividual first, ElkIndividual second,
				ElkIndividual... other);

		/**
		 * Create an {@link ElkDifferentIndividualsAxiom}.
		 * 
		 * @param individuals
		 *            the {@link ElkIndividual}s for which the axiom should be
		 *            created
		 * @return an {@link ElkDifferentIndividualsAxiom} corresponding to the
		 *         input
		 */
		public ElkDifferentIndividualsAxiom getDifferentIndividualsAxiom(
				List<? extends ElkIndividual> individuals);

	}

}
