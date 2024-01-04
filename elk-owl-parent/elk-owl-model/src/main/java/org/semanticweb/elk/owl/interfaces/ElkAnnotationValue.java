
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkAnnotationValueVisitor;

/**
 * Either an IRI, an anonymous individial, or a literal, as defined in
 * <a href="http://www.w3.org/TR/owl2-syntax/#Annotations">Section 10</a> of the
 * specification.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *
 */
public interface ElkAnnotationValue extends ElkObject {

	/**
	 * Accept an {@link ElkAnnotationValueVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnnotationValueVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends ElkAnonymousIndividual.Factory, ElkLiteral.Factory {

		// combined interface

	}

}
