
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinalityUnqualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDataMaxCardinalityUnqualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataMaxCardinalityUnqualifiedVisitor<O> {

	public O visit(ElkDataMaxCardinalityUnqualified expression);

}
