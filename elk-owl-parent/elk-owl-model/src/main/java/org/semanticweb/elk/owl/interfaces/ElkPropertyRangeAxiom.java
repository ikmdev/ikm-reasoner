
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;

/**
 * A generic interface for for object and data property range axioms.
 * properties.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 *            the type of the property of this axiom
 * @param <R>
 *            the type of the range of this axiom
 */
public interface ElkPropertyRangeAxiom<P, R> extends ElkPropertyAxiom<P> {

	/**
	 * Get the range of this axiom.
	 * 
	 * @return the range of this axiom
	 */
	R getRange();

	/**
	 * Accept an {@link ElkPropertyRangeAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkPropertyRangeAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkAnnotationPropertyRangeAxiom.Factory,
			ElkDataPropertyRangeAxiom.Factory,
			ElkObjectPropertyRangeAxiom.Factory {

	}

}
