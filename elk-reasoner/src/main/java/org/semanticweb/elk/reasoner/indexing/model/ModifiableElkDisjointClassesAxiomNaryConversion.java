
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * An {@link ElkDisjointClassesAxiomNaryConversion} that can be modified as a
 * result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkDisjointClassesAxiomNaryConversion
		extends ElkDisjointClassesAxiomNaryConversion,
		ModifiableIndexedDisjointClassesAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkDisjointClassesAxiomNaryConversion getElkDisjointClassesAxiomNaryConversion(
				ElkDisjointClassesAxiom originalAxiom,
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

		O visit(ModifiableElkDisjointClassesAxiomNaryConversion inference);

	}

}
