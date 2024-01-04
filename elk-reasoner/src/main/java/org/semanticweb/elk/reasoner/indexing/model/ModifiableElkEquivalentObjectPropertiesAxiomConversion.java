
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;

/**
 * An {@link ElkEquivalentObjectPropertiesAxiomConversion} that can be modified
 * as a result of updating the {@link ModifiableOntologyIndex} where this object
 * is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkEquivalentObjectPropertiesAxiomConversion
		extends ElkEquivalentObjectPropertiesAxiomConversion,
		ModifiableIndexedSubObjectPropertyOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkEquivalentObjectPropertiesAxiomConversion getElkEquivalentObjectPropertiesAxiomConversion(
				ElkEquivalentObjectPropertiesAxiom originalAxiom,
				int subPropertyPosition, int superPropertyPosition,
				ModifiableIndexedObjectProperty subProperty,
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

		O visit(ModifiableElkEquivalentObjectPropertiesAxiomConversion inference);

	}

}
