
package org.semanticweb.elk.reasoner.query;

import org.semanticweb.elk.exceptions.ElkException;

public class ElkQueryException extends ElkException {
	private static final long serialVersionUID = -4003447620410594100L;

	public ElkQueryException() {
		super();
	}

	public ElkQueryException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ElkQueryException(final String message) {
		super(message);
	}

	public ElkQueryException(final Throwable cause) {
		super(cause);
	}

}
