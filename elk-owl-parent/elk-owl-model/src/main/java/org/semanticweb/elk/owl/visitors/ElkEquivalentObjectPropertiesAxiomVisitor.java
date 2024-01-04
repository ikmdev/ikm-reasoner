
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkEquivalentObjectPropertiesAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkEquivalentObjectPropertiesAxiomVisitor<O> {

	public O visit(ElkEquivalentObjectPropertiesAxiom axiom);

}
