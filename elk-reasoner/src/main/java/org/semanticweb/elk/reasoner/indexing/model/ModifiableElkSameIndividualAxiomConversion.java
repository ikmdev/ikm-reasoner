
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;

/**
 * An {@link ElkSameIndividualAxiomConversion} that can be modified as a result
 * of updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkSameIndividualAxiomConversion
		extends ElkSameIndividualAxiomConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkSameIndividualAxiomConversion getElkSameIndividualAxiomConversion(
				ElkSameIndividualAxiom originalAxiom, int subIndividualPosition,
				int superIndividualPosition,
				ModifiableIndexedIndividual subIndividual,
				ModifiableIndexedIndividual superIndividual);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ModifiableElkSameIndividualAxiomConversion inference);

	}

}
