
package org.semanticweb.elk.owl.parsing.javacc;



/**
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ConcurrentJavaCCLexerFactory implements
		JavaCCLexerFactory<AbstractOwl2FunctionalStyleParserTokenManager> {

	@Override
	public AbstractOwl2FunctionalStyleParserTokenManager createLexer(
			AbstractOwl2FunctionalStyleParserTokenManager nativeLexer) {

		return new ConcurrentJavaCCLexer(nativeLexer);
	}

}