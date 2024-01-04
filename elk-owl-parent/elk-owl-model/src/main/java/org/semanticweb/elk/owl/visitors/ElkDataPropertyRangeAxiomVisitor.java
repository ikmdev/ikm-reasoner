
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataPropertyRangeAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkDataPropertyRangeAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataPropertyRangeAxiomVisitor<O> {

	public O visit(ElkDataPropertyRangeAxiom axiom);

}
