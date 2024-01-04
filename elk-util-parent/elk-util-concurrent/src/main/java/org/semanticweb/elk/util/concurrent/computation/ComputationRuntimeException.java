
package org.semanticweb.elk.util.concurrent.computation;

public class ComputationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1265120456168538105L;

	public ComputationRuntimeException() {
		super();
	}

	public ComputationRuntimeException(String message) {
		super(message);
	}

	public ComputationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComputationRuntimeException(Throwable cause) {
		super(cause);
	}

}
