
package org.semanticweb.elk.reasoner;

/**
 * Exception that is thrown when the reasoner is asked to perform a reasoning
 * task that is not supported.
 * 
 * 
 * @author Frantisek Simancik
 * 
 */
public class ElkUnsupportedReasoningTaskException extends
		UnsupportedOperationException {

	private static final long serialVersionUID = -2071488004689150749L;

	public ElkUnsupportedReasoningTaskException() {
		super();
	}

	public ElkUnsupportedReasoningTaskException(String message) {
		super(message);
	}

	public ElkUnsupportedReasoningTaskException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkUnsupportedReasoningTaskException(Throwable cause) {
		super(cause);
	}

}
