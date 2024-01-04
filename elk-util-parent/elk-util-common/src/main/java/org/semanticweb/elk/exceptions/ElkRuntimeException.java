
package org.semanticweb.elk.exceptions;

/**
 * The top level ELK runtime exception
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3395450547234499302L;

	public ElkRuntimeException() {
		super();
	}

	public ElkRuntimeException(String message) {
		super(message);
	}

	public ElkRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkRuntimeException(Throwable cause) {
		super(cause);
	}

}
