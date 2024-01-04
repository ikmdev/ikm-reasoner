
package org.semanticweb.elk.owl.parsing;

import org.semanticweb.elk.exceptions.ElkException;

/**
 * Base exception class for parsing exceptions
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 */
public class Owl2ParseException extends ElkException {

	private static final long serialVersionUID = -230059408691666695L;

	public Owl2ParseException(final Exception cause) {
		super(cause);
	}

	public Owl2ParseException() {
		super();
	}

	public Owl2ParseException(String message) {
		super(message);
	}

	public Owl2ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public Owl2ParseException(Throwable cause) {
		super(cause);
	}

}
