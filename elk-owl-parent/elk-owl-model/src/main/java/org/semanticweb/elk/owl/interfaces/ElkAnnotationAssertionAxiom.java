
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkAnnotationAssertionAxiomVisitor;

/**
 * Annotation assertion axiom as defined in
 * <a href="http://www.w3.org/TR/owl2-syntax/#Annotation_Assertion"> Section
 * 10.2.1</a> of the specification
 * 
 * Note that the superclass of AnnotationAssertion is AnnotationAxiom, not
 * AssertionAxiom.
 * 
 * @author Frantisek Simancik
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * 
 */
public interface ElkAnnotationAssertionAxiom extends ElkAnnotationAxiom {

	public ElkAnnotationSubject getSubject();

	public ElkAnnotationProperty getProperty();

	public ElkAnnotationValue getValue();

	/**
	 * Accept an {@link ElkAnnotationAssertionAxiomVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this axiom type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnnotationAssertionAxiomVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkAnnotationAssertionAxiom}
		 * 
		 * @param property
		 *            the {@link ElkAnnotationProperty} for which the axiom
		 *            should be created
		 * @param subject
		 *            the {@link ElkAnnotationSubject} for which the axiom
		 *            should be created
		 * @param value
		 *            the {@link ElkAnnotationValue} for which the axiom should
		 *            be created
		 * @return an {@link ElkAnnotationAssertionAxiom} corresponding to the
		 *         input
		 */
		public ElkAnnotationAssertionAxiom getAnnotationAssertionAxiom(
				ElkAnnotationProperty property, ElkAnnotationSubject subject,
				ElkAnnotationValue value);

	}
}
