
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;

/**
 * Visitor pattern interface for instances of {@link ElkObjectUnionOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectUnionOfVisitor<O> {

	public O visit(ElkObjectUnionOf expression);

}
