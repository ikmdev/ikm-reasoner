
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityUnqualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectMaxCardinalityUnqualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectMaxCardinalityUnqualifiedVisitor<O> {

	public O visit(ElkObjectMaxCardinalityUnqualified expression);

}
