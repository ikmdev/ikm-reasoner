
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityUnqualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDataExactCardinalityUnqualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataExactCardinalityUnqualifiedVisitor<O> {

	public O visit(ElkDataExactCardinalityUnqualified expression);

}
