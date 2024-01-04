
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;

/**
 * Represents a transformation of an {@link ElkObjectPropertyDomainAxiom} to an
 * {@link IndexedSubClassOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 */
public interface ElkObjectPropertyDomainAxiomConversion
		extends
			IndexedSubClassOfAxiomInference {

	@Override
	ElkObjectPropertyDomainAxiom getOriginalAxiom();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkObjectPropertyDomainAxiomConversion inference);

	}

}
