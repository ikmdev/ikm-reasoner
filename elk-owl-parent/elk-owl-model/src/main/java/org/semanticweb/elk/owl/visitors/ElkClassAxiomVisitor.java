
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkClassAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkClassAxiom}.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkClassAxiomVisitor<O> extends
		ElkDisjointClassesAxiomVisitor<O>, ElkDisjointUnionAxiomVisitor<O>,
		ElkEquivalentClassesAxiomVisitor<O>, ElkSubClassOfAxiomVisitor<O> {

	// combined visitor

}
