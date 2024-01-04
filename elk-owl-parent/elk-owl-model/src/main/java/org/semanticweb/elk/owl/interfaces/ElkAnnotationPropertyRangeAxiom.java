
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyRangeAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Annotation_Property_Range">
 * Annotation Property Range<a> in the OWL 2 specification.
 * 
 * @author Frantisek Simancik
 *
 */
public interface ElkAnnotationPropertyRangeAxiom extends ElkAnnotationAxiom,
		ElkPropertyRangeAxiom<ElkAnnotationProperty, ElkIri> {

	/**
	 * Accept an {@link ElkAnnotationPropertyRangeAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnnotationPropertyRangeAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkAnnotationPropertyRangeAxiom}
		 * 
		 * @param property
		 *            the {@link ElkAnnotationProperty} for which the axiom
		 *            should be created
		 * @param range
		 *            the {@link ElkIri} for which the object axiom be created
		 * @return an {@link ElkAnnotationPropertyRangeAxiom} corresponding to
		 *         the input
		 */
		public ElkAnnotationPropertyRangeAxiom getAnnotationPropertyRangeAxiom(
				ElkAnnotationProperty property, ElkIri range);

	}

}
