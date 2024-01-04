
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * An {@link IndexedObjectPropertyRangeAxiom} that can be modified as a result
 * of updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableIndexedObjectPropertyRangeAxiom
		extends ModifiableIndexedAxiom, IndexedObjectPropertyRangeAxiom {

	@Override
	ModifiableIndexedObjectProperty getProperty();

	@Override
	ModifiableIndexedClassExpression getRange();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedObjectPropertyRangeAxiom getIndexedObjectPropertyRangeAxiom(
				ElkAxiom originalAxiom,
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

		O visit(ModifiableIndexedObjectPropertyRangeAxiom axiom);

	}

}
