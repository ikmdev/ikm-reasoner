
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataUnionOf;

/**
 * Visitor pattern interface for instances of {@link ElkDataUnionOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataUnionOfVisitor<O> {

	public O visit(ElkDataUnionOf expression);

}
