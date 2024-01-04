
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;

/**
 * Represents a transformation of an {@link ElkSubObjectPropertyOfAxiom} to an
 * {@link IndexedSubObjectPropertyOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkSubObjectPropertyOfAxiomConversion
		extends
			IndexedSubObjectPropertyOfAxiomInference {

	@Override
	ElkSubObjectPropertyOfAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkSubObjectPropertyOfAxiomConversion inference);

	}

}
