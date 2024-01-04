
package org.semanticweb.elk.owl.parsing.javacc;


/**
 * A {@link LexerMessage} that indicates lexing error
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
class LexerError implements LexerMessage {

	private Error error_;

	public LexerError(Error error) {
		this.error_ = error;
	}

	public Error getError() {
		return error_;
	}

	@Override
	public void accept(LexerMessageVisitor visitor) {
		visitor.visit(this);
	}
}
