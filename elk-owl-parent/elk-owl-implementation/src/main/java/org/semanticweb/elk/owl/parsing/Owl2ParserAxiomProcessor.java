 
package org.semanticweb.elk.owl.parsing;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.iris.ElkPrefix;

/**
 * Objects that can process {@link ElkAxiom}s and throw
 * {@link Owl2ParseException}s. Intended to be used in {@link Owl2Parser}s
 * 
 * @see Owl2Parser
 * 
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface Owl2ParserAxiomProcessor {

	public void visit(ElkAxiom elkAxiom) throws Owl2ParseException;
	public void visit(ElkPrefix elkPrefix) throws Owl2ParseException;
	public void finish() throws Owl2ParseException;

}
