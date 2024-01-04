
package org.semanticweb.elk.reasoner;

import org.semanticweb.elk.exceptions.ElkException;

/**
 * Thrown when irrelevant reasoning methods are called for an ontology that is
 * inconsistent. Most reasoning tasks also have well-defined results for
 * inconsistent ontologies, so it is not required that this exception is used in
 * all cases. Callers should not rely on this exception being thrown as a method
 * for checking inconsistency; there are dedicated methods for this purpose.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkInconsistentOntologyException extends ElkException {

	private static final long serialVersionUID = -8696304480425201859L;

	public ElkInconsistentOntologyException() {
		super();
	}

	public ElkInconsistentOntologyException(String message) {
		super(message);
	}

	public ElkInconsistentOntologyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkInconsistentOntologyException(Throwable cause) {
		super(cause);
	}

}
