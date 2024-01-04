
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkInverseObjectPropertiesAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkInverseObjectPropertiesAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkInverseObjectPropertiesAxiomVisitor<O> {

	public O visit(ElkInverseObjectPropertiesAxiom axiom);

}
