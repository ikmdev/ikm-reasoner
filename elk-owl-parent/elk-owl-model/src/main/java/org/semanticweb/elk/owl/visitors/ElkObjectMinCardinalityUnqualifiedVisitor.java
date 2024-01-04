
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityUnqualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectMinCardinalityUnqualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectMinCardinalityUnqualifiedVisitor<O> {

	public O visit(ElkObjectMinCardinalityUnqualified expression);

}
