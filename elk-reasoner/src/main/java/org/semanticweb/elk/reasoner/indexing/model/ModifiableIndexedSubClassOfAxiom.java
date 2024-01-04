
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



/**
 * An {@link IndexedSubClassOfAxiom} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedSubClassOfAxiom
		extends ModifiableIndexedAxiom, IndexedSubClassOfAxiom {

	@Override
	ModifiableIndexedClassExpression getSubClass();

	@Override
	ModifiableIndexedClassExpression getSuperClass();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		public ModifiableIndexedSubClassOfAxiom getIndexedSubClassOfAxiom(
				ElkAxiom originalAxiom,
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

		O visit(ModifiableIndexedSubClassOfAxiom axiom);

	}

}
