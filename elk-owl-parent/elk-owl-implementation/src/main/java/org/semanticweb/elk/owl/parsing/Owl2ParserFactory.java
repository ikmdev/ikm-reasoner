 
package org.semanticweb.elk.owl.parsing;

import java.io.InputStream;
import java.io.Reader;

public interface Owl2ParserFactory {

	public Owl2Parser getParser(InputStream stream);

	public Owl2Parser getParser(Reader reader);

}
