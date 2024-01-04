
package org.semanticweb.elk.loading;

import org.semanticweb.elk.exceptions.ElkException;

public class ElkLoadingException extends ElkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -272682717729938704L;

	public ElkLoadingException() {
		super();
	}

	public ElkLoadingException(String message) {
		super(message);
	}

	public ElkLoadingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkLoadingException(Throwable cause) {
		super(cause);
	}

}
