
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;

/**
 * Visitor pattern interface for instances of {@link ElkObjectIntersectionOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectIntersectionOfVisitor<O> {

	public O visit(ElkObjectIntersectionOf expression);

}
