
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkSameIndividualAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Individual_Equality">individual
 * equality axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkSameIndividualAxiom extends ElkAssertionAxiom {

	/**
	 * Get the list of individuals that this axiom refers to. The order of
	 * individuals does not affect the semantics but it is relevant to the
	 * syntax of OWL.
	 * 
	 * @return list of individuals
	 */
	public List<? extends ElkIndividual> getIndividuals();

	/**
	 * Accept an {@link ElkSameIndividualAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkSameIndividualAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkSameIndividualAxiom}.
		 * 
		 * @param first
		 *            the first equivalent {@link ElkIndividual} for which the
		 *            axiom should be created
		 * @param second
		 *            the second equivalent {@link ElkIndividual} for which the
		 *            axiom should be created
		 * @param other
		 *            other equivalent {@link ElkIndividual} for which the axiom
		 *            should be created
		 * @return an {@link ElkSameIndividualAxiom} corresponding to the input
		 */
		public ElkSameIndividualAxiom getSameIndividualAxiom(
				ElkIndividual first, ElkIndividual second,
				ElkIndividual... other);

		/**
		 * Create an {@link ElkSameIndividualAxiom}.
		 * 
		 * @param individuals
		 *            the equivalent {@link ElkIndividual} for which the axiom
		 *            should be created
		 * @return an {@link ElkSameIndividualAxiom} corresponding to the input
		 */
		public ElkSameIndividualAxiom getSameIndividualAxiom(
				List<? extends ElkIndividual> individuals);
	}

}
