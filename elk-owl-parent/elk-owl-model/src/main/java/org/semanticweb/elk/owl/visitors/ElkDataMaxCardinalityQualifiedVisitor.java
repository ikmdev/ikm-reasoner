
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinalityQualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDataMaxCardinalityQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataMaxCardinalityQualifiedVisitor<O> {

	public O visit(ElkDataMaxCardinalityQualified expression);

}
