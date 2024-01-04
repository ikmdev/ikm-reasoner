
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * An {@link ElkDisjointUnionAxiomSubClassConversion} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkDisjointUnionAxiomSubClassConversion
		extends ElkDisjointUnionAxiomSubClassConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkDisjointUnionAxiomSubClassConversion getElkDisjointUnionAxiomSubClassConversion(
				ElkDisjointUnionAxiom originalAxiom, int disjunctPosition,
				ModifiableIndexedClassExpression disjunct,
				ModifiableIndexedClass definedClass);

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

		O visit(ModifiableElkDisjointUnionAxiomSubClassConversion inference);

	}

}
