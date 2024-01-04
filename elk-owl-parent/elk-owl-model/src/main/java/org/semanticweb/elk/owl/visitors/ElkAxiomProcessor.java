
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Objects that can process ELK axioms
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAxiomProcessor {

	public void visit(ElkAxiom elkAxiom);

}
