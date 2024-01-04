 
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;

/**
 * An {@link ElkDisjointUnionAxiomOwlNothingConversion} that can be modified as
 * a result of updating the {@link ModifiableOntologyIndex} where this object is
 * stored.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ModifiableElkDisjointUnionAxiomOwlNothingConversion
		extends ElkDisjointUnionAxiomOwlNothingConversion,
		ModifiableIndexedSubClassOfAxiomInference {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableElkDisjointUnionAxiomOwlNothingConversion getElkDisjointUnionAxiomOwlNothingConversion(
				ElkDisjointUnionAxiom originalAxiom,
				ModifiableIndexedClass definedClass,
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

		O visit(ModifiableElkDisjointUnionAxiomOwlNothingConversion inference);

	}

}
