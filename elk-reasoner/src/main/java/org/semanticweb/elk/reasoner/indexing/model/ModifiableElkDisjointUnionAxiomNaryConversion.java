
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * An {@link ElkDisjointUnionAxiomNaryConversion} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkDisjointUnionAxiomNaryConversion
		extends ElkDisjointUnionAxiomNaryConversion,
		ModifiableIndexedDisjointClassesAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkDisjointUnionAxiomNaryConversion getElkDisjointUnionAxiomNaryConversion(
				ElkDisjointUnionAxiom originalAxiom,
				ModifiableIndexedClassExpressionList disjointClasses);

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

		O visit(ModifiableElkDisjointUnionAxiomNaryConversion inference);

	}

}
