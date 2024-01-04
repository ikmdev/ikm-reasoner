
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * An {@link ElkDisjointClassesAxiomBinaryConversion} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkDisjointClassesAxiomBinaryConversion
		extends ElkDisjointClassesAxiomBinaryConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkDisjointClassesAxiomBinaryConversion getElkDisjointClassesAxiomBinaryConversion(
				ElkDisjointClassesAxiom originalAxiom, int firstClassPosition,
				int secondClassPosition,
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

		O visit(ModifiableElkDisjointClassesAxiomBinaryConversion inference);

	}

}
