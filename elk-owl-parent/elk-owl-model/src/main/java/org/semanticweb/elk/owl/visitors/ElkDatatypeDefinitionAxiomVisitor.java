
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDatatypeDefinitionAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkDatatypeDefinitionAxiom}
 * .
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDatatypeDefinitionAxiomVisitor<O> {

	O visit(ElkDatatypeDefinitionAxiom axiom);
}
