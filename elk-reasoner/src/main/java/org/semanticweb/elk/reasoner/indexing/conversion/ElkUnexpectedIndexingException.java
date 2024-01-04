
package org.semanticweb.elk.reasoner.indexing.conversion;

import org.semanticweb.elk.reasoner.indexing.model.IndexedObject;



/**
 * An exception to signal incorrect indexing behavior.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkUnexpectedIndexingException extends ElkIndexingException {

	private static final long serialVersionUID = -6297215279078361253L;

	protected ElkUnexpectedIndexingException() {
	}

	public ElkUnexpectedIndexingException(String message) {
		super(message);
	}

	public ElkUnexpectedIndexingException(IndexedObject object) {
		super("Error indexing " + object);
	}

	public ElkUnexpectedIndexingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElkUnexpectedIndexingException(Throwable cause) {
		super(cause);
	}

}
