
package org.semanticweb.elk.owl.interfaces;

import org.semanticweb.elk.owl.visitors.ElkAnnotationSubjectVisitor;

/**
 * Either an IRI or an anonymous individual
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *
 */
public interface ElkAnnotationSubject extends ElkObject {

	/**
	 * Accept an {@link ElkAnnotationSubjectVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkAnnotationSubjectVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkAnonymousIndividual.Factory {

		// combined interface

	}

}
