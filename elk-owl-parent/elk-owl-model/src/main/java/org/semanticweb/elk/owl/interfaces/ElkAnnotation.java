
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkAnnotationVisitor;

/**
 * The basic interface for annotation objects in OWL 2 as defined in
 * <a href="http://www.w3.org/TR/owl2-syntax/#Annotations">Section 10</a> of the
 * specification
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 */

/*
 * TODO: Annotations are not included in our model. We should extend the model
 * so that every ontology, axiom, and annotation can link to a list of
 * annotations.
 */

public interface ElkAnnotation extends ElkObject {

	public ElkAnnotationProperty getProperty();

	public ElkAnnotationValue getValue();

	/**
	 * Accept an {@link ElkAnnotationVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnnotationVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkAnnotation}
		 * 
		 * @param property
		 *            the {@link ElkAnnotationProperty} for which the object
		 *            should be created
		 * @param value
		 *            the {@link ElkAnnotationValue} for which the object should
		 *            be created
		 * @return an {@link ElkAnnotation} corresponding to the input
		 */
		public ElkAnnotation getAnnotation(ElkAnnotationProperty property,
				ElkAnnotationValue value);

	}
	
}
