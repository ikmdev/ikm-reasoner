
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityQualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDataExactCardinalityQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataExactCardinalityQualifiedVisitor<O> {

	public O visit(ElkDataExactCardinalityQualified expression);

}
