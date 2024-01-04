
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;

/**
 * An {@link ElkClassAssertionAxiomConversion} that can be modified as a result
 * of updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableElkClassAssertionAxiomConversion
		extends ElkClassAssertionAxiomConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkClassAssertionAxiomConversion getElkClassAssertionAxiomConversion(
				ElkClassAssertionAxiom originalAxiom,
				ModifiableIndexedIndividual instance,
				ModifiableIndexedClassExpression type);

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

		O visit(ModifiableElkClassAssertionAxiomConversion inference);

	}

}
