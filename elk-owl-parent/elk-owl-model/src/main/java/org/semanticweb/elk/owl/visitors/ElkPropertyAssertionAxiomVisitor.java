
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkPropertyAssertionAxiom;



/**
 * Visitor pattern interface for instances of {@link ElkPropertyAssertionAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkPropertyAssertionAxiomVisitor<O> extends
		ElkDataPropertyAssertionAxiomVisitor<O>,
		ElkNegativeDataPropertyAssertionAxiomVisitor<O>,
		ElkNegativeObjectPropertyAssertionAxiomVisitor<O>,
		ElkObjectPropertyAssertionAxiomVisitor<O> {

	// combined visitor

}
