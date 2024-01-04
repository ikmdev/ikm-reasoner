
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityQualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectMaxCardinalityQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectMaxCardinalityQualifiedVisitor<O> {

	public O visit(ElkObjectMaxCardinalityQualified expression);

}
