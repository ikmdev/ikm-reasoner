
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityUnqualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDataMinCardinalityUnqualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataMinCardinalityUnqualifiedVisitor<O> {

	public O visit(ElkDataMinCardinalityUnqualified expression);

}
