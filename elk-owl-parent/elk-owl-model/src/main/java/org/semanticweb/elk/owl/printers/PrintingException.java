
package org.semanticweb.elk.owl.printers;

public class PrintingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1816369750840306252L;

	protected PrintingException() {
	}

	public PrintingException(String message) {
		super(message);
	}

	public PrintingException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrintingException(Throwable cause) {
		super(cause);
	}

}
