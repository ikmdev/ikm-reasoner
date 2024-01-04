
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyVisitor;

/**
 * @author Frantisek Simancik
 *
 */
public interface ElkAnnotationProperty extends ElkEntity {

	/**
	 * Accept an {@link ElkAnnotationPropertyVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnnotationPropertyVisitor<O> visitor);
	
	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkAnnotationProperty}.
		 * 
		 * @param iri
		 *            the {@link ElkIri} for which the object should be created
		 * @return an {@link ElkAnnotation} corresponding to the input
		 */
		public ElkAnnotationProperty getAnnotationProperty(ElkIri iri);

	}

}
