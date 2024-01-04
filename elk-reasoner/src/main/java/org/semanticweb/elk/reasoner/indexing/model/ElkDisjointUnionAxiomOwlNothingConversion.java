
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * Represents a transformation of an {@link ElkDisjointUnionAxiom} with the
 * empty list of disjoint class expressions to an {@link IndexedSubClassOfAxiom}
 * representing the inclusion between the defined class and {@code owl:Nothing}
 * 
 * 
 * @see ElkDisjointUnionAxiom#getClassExpressions()
 * @see ElkDisjointUnionAxiom#getDefinedClass()
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkDisjointUnionAxiomOwlNothingConversion
		extends IndexedSubClassOfAxiomInference {

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

		O visit(ElkDisjointUnionAxiomOwlNothingConversion inference);

	}

}
