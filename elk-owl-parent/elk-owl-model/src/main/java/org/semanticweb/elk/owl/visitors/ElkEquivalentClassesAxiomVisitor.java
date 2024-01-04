
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkEquivalentClassesAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkEquivalentClassesAxiomVisitor<O> {

	public O visit(ElkEquivalentClassesAxiom axiom);

}
