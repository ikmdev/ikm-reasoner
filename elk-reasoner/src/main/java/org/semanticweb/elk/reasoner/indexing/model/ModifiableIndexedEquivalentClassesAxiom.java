
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



public interface ModifiableIndexedEquivalentClassesAxiom
		extends ModifiableIndexedAxiom, IndexedEquivalentClassesAxiom {

	@Override
	ModifiableIndexedClassExpression getFirstMember();

	@Override
	ModifiableIndexedClassExpression getSecondMember();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedEquivalentClassesAxiom getIndexedEquivalentClassesAxiom(
				ElkAxiom originalAxiom,
				ModifiableIndexedClassExpression firstMember,
				ModifiableIndexedClassExpression secondMember);

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

		O visit(ModifiableIndexedEquivalentClassesAxiom axiom);

	}

}
