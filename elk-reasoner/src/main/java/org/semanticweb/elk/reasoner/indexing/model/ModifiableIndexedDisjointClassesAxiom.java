
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



/**
 * An {@link IndexedSubClassOfAxiom} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedDisjointClassesAxiom
		extends ModifiableIndexedAxiom, IndexedDisjointClassesAxiom {

	@Override
	ModifiableIndexedClassExpressionList getMembers();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedDisjointClassesAxiom getIndexedDisjointClassesAxiom(
				ElkAxiom originalAxiom,
				ModifiableIndexedClassExpressionList members);

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

		O visit(ModifiableIndexedDisjointClassesAxiom axiom);

	}

}
