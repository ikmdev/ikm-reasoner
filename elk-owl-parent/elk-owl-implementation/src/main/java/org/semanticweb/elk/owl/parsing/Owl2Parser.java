
package org.semanticweb.elk.owl.parsing;

import org.semanticweb.elk.owl.iris.ElkPrefix;

/**
 * The base interface for OWL 2 parsers
 * 
 * @author Pavel Klinov
 * @author "Yevgeny Kazakov"
 * 
 */
public interface Owl2Parser {

	/**
	 * Registers an additional prefix declaration for this parser, which can be
	 * used to resolve IRIs. Normally, prefix declarations should be parsed from
	 * OWL 2 files, but some prefix declarations, e.g., the OWL 2 predefined
	 * prefixes can be supplied separately.
	 * 
	 * @param elkPrefix
	 */
	public void declarePrefix(ElkPrefix elkPrefix);

	public void accept(Owl2ParserAxiomProcessor axiomProcessor)
			throws Owl2ParseException;

}
