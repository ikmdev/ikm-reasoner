
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyDomainAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Annotation_Property_Domain">
 * Annotation Property Domain<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public interface ElkAnnotationPropertyDomainAxiom extends ElkAnnotationAxiom,
		ElkPropertyDomainAxiom<ElkAnnotationProperty, ElkIri> {

	/**
	 * Accept an {@link ElkAnnotationPropertyDomainAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnnotationPropertyDomainAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkAnnotationPropertyDomainAxiom}
		 * 
		 * @param property
		 *            the {@link ElkAnnotationProperty} for which the axiom
		 *            should be created
		 * @param domain
		 *            the {@link ElkIri} for which the axiom should be created
		 * @return an {@link ElkAnnotationPropertyDomainAxiom} corresponding to
		 *         the input
		 */
		public ElkAnnotationPropertyDomainAxiom getAnnotationPropertyDomainAxiom(
				ElkAnnotationProperty property, ElkIri domain);

	}
}
