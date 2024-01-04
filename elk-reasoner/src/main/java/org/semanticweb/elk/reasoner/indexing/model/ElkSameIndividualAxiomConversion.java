
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;

/**
 * Represents a transformation of an {@link ElkSameIndividualAxiom} to an
 * {@link IndexedSubClassOfAxiom} representing inclusion between two selected
 * {@link ElkIndividual} members of {@link ElkSameIndividualAxiom}.
 * 
 * @see ElkSameIndividualAxiom#getIndividuals()
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkSameIndividualAxiomConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkSameIndividualAxiom getOriginalAxiom();

	/**
	 * @return the position of an {@link ElkIndividual} in the member list of
	 *         the {@link ElkSameIndividualAxiom} that is converted to the
	 *         sub-class of the {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see @see ElkSameIndividualAxiom#getIndividuals()
	 * @see IndexedSubClassOfAxiom#getSubClass()
	 */
	int getSubIndividualPosition();

	/**
	 * @return the position of an {@link ElkIndividual} in the member list of
	 *         the {@link ElkSameIndividualAxiom} that is converted to the
	 *         super-class of the {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see @see ElkSameIndividualAxiom#getIndividuals()
	 * @see IndexedSubClassOfAxiom#getSuperClass()
	 */
	int getSuperIndividualPosition();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkSameIndividualAxiomConversion inference);

	}

}
