
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;

/**
 * Represents a transformation of an {@link ElkObjectPropertyRangeAxiom} to an
 * {@link IndexedObjectPropertyRangeAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkObjectPropertyRangeAxiomConversion
		extends
			IndexedObjectPropertyRangeAxiomInference {

	@Override
	ElkObjectPropertyRangeAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkObjectPropertyRangeAxiomConversion inference);

	}

}
