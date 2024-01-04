
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkSameIndividualAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkSameIndividualAxiomVisitor<O> {

	public O visit(ElkSameIndividualAxiom axiom);

}
