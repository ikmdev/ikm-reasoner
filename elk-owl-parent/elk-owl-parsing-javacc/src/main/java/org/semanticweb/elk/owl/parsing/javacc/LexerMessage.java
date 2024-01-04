
package org.semanticweb.elk.owl.parsing.javacc;


/**
 * The abstract message type that can be send from the lexer thread
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface LexerMessage {
	public void accept(LexerMessageVisitor visitor);
}