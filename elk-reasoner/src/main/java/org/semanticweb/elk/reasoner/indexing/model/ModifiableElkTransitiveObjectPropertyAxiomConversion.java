
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;

/**
 * An {@link ElkTransitiveObjectPropertyAxiomConversion} that can be modified as
 * a result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkTransitiveObjectPropertyAxiomConversion
		extends ElkTransitiveObjectPropertyAxiomConversion,
		ModifiableIndexedSubObjectPropertyOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkTransitiveObjectPropertyAxiomConversion getElkTransitiveObjectPropertyAxiomConversion(
				ElkTransitiveObjectPropertyAxiom originalAxiom,
				ModifiableIndexedPropertyChain subPropertyChain,
				ModifiableIndexedObjectProperty superProperty);

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

		O visit(ModifiableElkTransitiveObjectPropertyAxiomConversion inference);

	}

}
