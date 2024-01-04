
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.exceptions.ElkException;

/**
 * Thrown when the reasoner process has been interrupted
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkInterruptedException extends ElkException {

	private static final long serialVersionUID = 7774912476379495291L;

	public ElkInterruptedException() {
		super();
	}

	public ElkInterruptedException(String message) {
		super(message);
	}

	public ElkInterruptedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkInterruptedException(Throwable cause) {
		super(cause);
	}

}
