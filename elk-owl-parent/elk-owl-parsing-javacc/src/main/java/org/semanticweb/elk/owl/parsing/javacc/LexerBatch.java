
package org.semanticweb.elk.owl.parsing.javacc;


import java.util.ArrayList;

/**
 * A {@link LexerMessage} that holds tokens
 * 
 * @author "Yevgeny Kazakov"
 */
class LexerBatch extends ArrayList<Token> implements LexerMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1438353325200022815L;

	public LexerBatch(int length) {
		super(length);
	}

	@Override
	public void accept(LexerMessageVisitor visitor) {
		visitor.visit(this);
	}
}