
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityUnqualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectExactCardinalityUnqualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectExactCardinalityUnqualifiedVisitor<O> {

	public O visit(ElkObjectExactCardinalityUnqualified expression);

}
