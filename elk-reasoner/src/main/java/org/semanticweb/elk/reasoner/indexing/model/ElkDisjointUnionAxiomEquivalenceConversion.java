
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * Represents a transformation of an {@link ElkDisjointUnionAxiom} to an
 * {@link IndexedEquivalentClassesAxiom}. This transformation is intended to be used
 * when the union consists of no more than one {@link ElkClassExpression}s.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkDisjointUnionAxiomEquivalenceConversion
		extends
			IndexedEquivalentClassesAxiomInference {

	@Override
	ElkDisjointUnionAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkDisjointUnionAxiomEquivalenceConversion inference);

	}

}
