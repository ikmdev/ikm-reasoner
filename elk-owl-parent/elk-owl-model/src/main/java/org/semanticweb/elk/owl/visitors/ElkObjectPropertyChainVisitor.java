
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;

/**
 * Visitor pattern interface for instances of {@link ElkObjectPropertyChain}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectPropertyChainVisitor<O> {

	public O visit(ElkObjectPropertyChain expression);

}
