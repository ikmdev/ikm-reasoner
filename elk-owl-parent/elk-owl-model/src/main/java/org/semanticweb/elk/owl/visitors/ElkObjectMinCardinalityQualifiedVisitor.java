
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityQualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkObjectMinCardinalityQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectMinCardinalityQualifiedVisitor<O> {

	public O visit(ElkObjectMinCardinalityQualified expression);

}
