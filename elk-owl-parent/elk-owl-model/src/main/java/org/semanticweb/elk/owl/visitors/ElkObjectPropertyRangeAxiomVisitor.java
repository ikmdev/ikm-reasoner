
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectPropertyRangeAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectPropertyRangeAxiomVisitor<O> {

	public O visit(ElkObjectPropertyRangeAxiom axiom);

}
