
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;

/**
 * Represents a transformation of an {@link ElkTransitiveObjectPropertyAxiom} to
 * an {@link IndexedSubObjectPropertyOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkTransitiveObjectPropertyAxiomConversion
		extends
			IndexedSubObjectPropertyOfAxiomInference {

	@Override
	ElkTransitiveObjectPropertyAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkTransitiveObjectPropertyAxiomConversion inference);

	}

}
