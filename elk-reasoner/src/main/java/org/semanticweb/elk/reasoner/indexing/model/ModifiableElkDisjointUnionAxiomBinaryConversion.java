
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * An {@link ElkDisjointUnionAxiomBinaryConversion} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkDisjointUnionAxiomBinaryConversion
		extends ElkDisjointUnionAxiomBinaryConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkDisjointUnionAxiomBinaryConversion getElkDisjointUnionAxiomBinaryConversion(
				ElkDisjointUnionAxiom originalAxiom, int firstDisjunctPosition,
				int secondDisjunctPosition,
				ModifiableIndexedObjectIntersectionOf conjunction,
				ModifiableIndexedClass bottom);

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

		O visit(ModifiableElkDisjointUnionAxiomBinaryConversion inference);

	}

}
