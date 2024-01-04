
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAxiomVisitor<O> extends ElkAnnotationAxiomVisitor<O>,
		ElkAssertionAxiomVisitor<O>, ElkClassAxiomVisitor<O>,
		ElkDataPropertyAxiomVisitor<O>, ElkDatatypeDefinitionAxiomVisitor<O>,
		ElkDeclarationAxiomVisitor<O>, ElkHasKeyAxiomVisitor<O>,
		ElkObjectPropertyAxiomVisitor<O>, ElkPropertyAxiomVisitor<O>,
		ElkSWRLRuleVisitor<O> {

	// combined visitor
}
