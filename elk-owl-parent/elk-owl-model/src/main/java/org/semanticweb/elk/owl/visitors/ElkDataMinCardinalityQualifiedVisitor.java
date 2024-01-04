
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityQualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkDataMinCardinalityQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataMinCardinalityQualifiedVisitor<O> {

	public O visit(ElkDataMinCardinalityQualified expression);

}
