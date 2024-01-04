
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityQualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectExactCardinalityQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectExactCardinalityQualifiedVisitor<O> {

	public O visit(ElkObjectExactCardinalityQualified expression);

}
