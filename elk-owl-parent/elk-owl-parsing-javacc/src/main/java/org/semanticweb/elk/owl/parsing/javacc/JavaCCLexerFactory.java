
package org.semanticweb.elk.owl.parsing.javacc;


/**
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 * @param <T> 
 */
public interface JavaCCLexerFactory<T> {

	/**
	 * 
	 * @param nativeLexer The lexer which has physical access to the input stream of characters
	 * @return a JavaCC warpper lexer
	 */
	public T createLexer(T nativeLexer);
}
