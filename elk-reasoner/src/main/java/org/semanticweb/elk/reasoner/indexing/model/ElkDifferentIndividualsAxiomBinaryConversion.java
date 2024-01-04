
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;

/**
 * Represents a transformation of an {@link ElkDifferentIndividualsAxiom} to an
 * {@link IndexedSubClassOfAxiom} representing that two selected
 * {@link ElkIndividual} members are different.
 *
 * @see ElkDifferentIndividualsAxiom#getIndividuals()
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkDifferentIndividualsAxiomBinaryConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkDifferentIndividualsAxiom getOriginalAxiom();
	
	/**
	 * @return the position of the first {@link ElkIndividual} in the member
	 *         list of the {@link ElkDifferentIndividualsAxiom} whose difference
	 *         with the second one is expressed by the resulting
	 *         {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkDifferentIndividualsAxiom#getIndividuals()
	 * @see #getSecondIndividualPosition()
	 */
	int getFirstIndividualPosition();

	/**
	 * @return the position of the second {@link ElkIndividual} in the member
	 *         list of the {@link ElkDifferentIndividualsAxiom} whose difference
	 *         with the first one is expressed by the resulting
	 *         {@link IndexedSubClassOfAxiom}.
	 * 
	 * @see ElkDifferentIndividualsAxiom#getIndividuals()
	 * @see #getFirstIndividualPosition()
	 */
	int getSecondIndividualPosition();	

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDifferentIndividualsAxiomBinaryConversion inference);

	}

}
