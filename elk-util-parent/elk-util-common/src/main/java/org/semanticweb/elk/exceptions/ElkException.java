
package org.semanticweb.elk.exceptions;

/**
 * The top level ELK exception
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public abstract class ElkException extends Exception {

	/**
	 *  
	 */
	private static final long serialVersionUID = -8363638439300197568L;

	public ElkException() {
		super();
	}

	public ElkException(String message) {
		super(message);
	}

	public ElkException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkException(Throwable cause) {
		super(cause);
	}

}
