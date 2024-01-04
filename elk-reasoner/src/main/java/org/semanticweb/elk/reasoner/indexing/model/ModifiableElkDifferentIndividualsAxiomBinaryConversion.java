
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;

/**
 * An {@link ElkDifferentIndividualsAxiomBinaryConversion} that can be modified
 * as a result of updating the {@link ModifiableOntologyIndex} where this object
 * is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableElkDifferentIndividualsAxiomBinaryConversion
		extends
			ElkDifferentIndividualsAxiomBinaryConversion,
			ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkDifferentIndividualsAxiomBinaryConversion getElkDifferentIndividualsAxiomBinaryConversion(
				ElkDifferentIndividualsAxiom originalAxiom,
				int firstIndividualPosition, int secondIndividualPosition,
				ModifiableIndexedObjectIntersectionOf conjunction,
				ModifiableIndexedClass bottom);

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

		O visit(ModifiableElkDifferentIndividualsAxiomBinaryConversion inference);

	}


}
