
package org.semanticweb.elk.reasoner.indexing.conversion;

import org.semanticweb.elk.exceptions.ElkRuntimeException;

/**
 * An exception to signal indexing problems
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkIndexingException extends ElkRuntimeException {

	private static final long serialVersionUID = -8678875783274619601L;

	protected ElkIndexingException() {
	}

	public ElkIndexingException(String message) {
		super(message);
	}

	public ElkIndexingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkIndexingException(Throwable cause) {
		super(cause);
	}

}
