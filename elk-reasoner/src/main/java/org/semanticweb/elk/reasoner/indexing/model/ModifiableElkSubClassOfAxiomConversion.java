
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * An {@link ElkSubClassOfAxiomConversion} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkSubClassOfAxiomConversion
		extends ElkSubClassOfAxiomConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkSubClassOfAxiomConversion getElkSubClassOfAxiomConversion(
				ElkSubClassOfAxiom originalAxiom,
				ModifiableIndexedClassExpression subClass,
				ModifiableIndexedClassExpression superClass);

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

		O visit(ModifiableElkSubClassOfAxiomConversion inference);

	}

}
