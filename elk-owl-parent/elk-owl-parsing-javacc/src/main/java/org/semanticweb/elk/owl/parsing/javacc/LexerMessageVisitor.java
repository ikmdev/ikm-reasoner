
package org.semanticweb.elk.owl.parsing.javacc;



/**
 * The visitor pattern for {@link LexerMessage}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
interface LexerMessageVisitor {

	public void visit(LexerBatch batch);

	public void visit(LexerError error);

}
