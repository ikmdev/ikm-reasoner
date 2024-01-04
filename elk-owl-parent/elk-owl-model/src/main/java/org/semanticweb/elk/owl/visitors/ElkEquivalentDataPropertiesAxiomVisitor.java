
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentDataPropertiesAxiom;

/**
 * Visitor pattern interface for instances of
 * {@link ElkEquivalentDataPropertiesAxiom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkEquivalentDataPropertiesAxiomVisitor<O> {

	public O visit(ElkEquivalentDataPropertiesAxiom axiom);

}
