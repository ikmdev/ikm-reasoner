
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;

/**
 * An {@link ElkObjectPropertyRangeAxiomConversion} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkObjectPropertyRangeAxiomConversion
		extends ElkObjectPropertyRangeAxiomConversion,
		ModifiableIndexedObjectPropertyRangeAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkObjectPropertyRangeAxiomConversion getElkObjectPropertyRangeAxiomConversion(
				ElkObjectPropertyRangeAxiom originalAxiom,
				ModifiableIndexedObjectProperty property,
				ModifiableIndexedClassExpression range);

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

		O visit(ModifiableElkObjectPropertyRangeAxiomConversion inference);

	}

}
