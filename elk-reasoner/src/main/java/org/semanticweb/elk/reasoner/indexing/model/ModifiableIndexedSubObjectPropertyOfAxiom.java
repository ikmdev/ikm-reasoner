
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



/**
 * An {@link IndexedSubObjectPropertyOfAxiom} that can be modified as a result
 * of updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedSubObjectPropertyOfAxiom
		extends ModifiableIndexedAxiom, IndexedSubObjectPropertyOfAxiom {

	@Override
	ModifiableIndexedPropertyChain getSubPropertyChain();

	@Override
	ModifiableIndexedObjectProperty getSuperProperty();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedSubObjectPropertyOfAxiom getIndexedSubObjectPropertyOfAxiom(
				ElkAxiom originalAxiom,
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

		O visit(ModifiableIndexedSubObjectPropertyOfAxiom axiom);

	}

}
