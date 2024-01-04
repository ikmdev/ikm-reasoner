
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkSubAnnotationPropertyOfAxiomVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Annotation_Subproperties">
 * Annotation Subproperty Axiom<a> in the OWL 2 specification.
 * 
 * @author Frantisek Simancik
 *
 */
public interface ElkSubAnnotationPropertyOfAxiom extends ElkAnnotationAxiom {

	/**
	 * Get the sub annotation property of this axiom.
	 * 
	 * @return sub annotation property
	 */
	public ElkAnnotationProperty getSubAnnotationProperty();

	/**
	 * Get the super annotation property of this axiom.
	 * 
	 * @return super annotation property
	 */
	public ElkAnnotationProperty getSuperAnnotationProperty();

	/**
	 * Accept an {@link ElkSubAnnotationPropertyOfAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkSubAnnotationPropertyOfAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkSubAnnotationPropertyOfAxiom}
		 * 
		 * @param subProperty
		 *            the sub-{@link ElkAnnotationProperty} for which the object
		 *            should be created
		 * @param superProperty
		 *            the super-{@link ElkAnnotationProperty} for which the
		 *            object should be created
		 * @return an {@link ElkSubAnnotationPropertyOfAxiom} corresponding to
		 *         the input
		 */
		public ElkSubAnnotationPropertyOfAxiom getSubAnnotationPropertyOfAxiom(
				ElkAnnotationProperty subProperty,
				ElkAnnotationProperty superProperty);
	}

}
