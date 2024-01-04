
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;

/**
 * An {@link ElkObjectPropertyAssertionAxiomConversion} that can be modified as
 * a result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkObjectPropertyAssertionAxiomConversion
		extends ElkObjectPropertyAssertionAxiomConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkObjectPropertyAssertionAxiomConversion getElkObjectPropertyAssertionAxiomConversion(
				ElkObjectPropertyAssertionAxiom originalAxiom,
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

		O visit(ModifiableElkObjectPropertyAssertionAxiomConversion inference);

	}

}
