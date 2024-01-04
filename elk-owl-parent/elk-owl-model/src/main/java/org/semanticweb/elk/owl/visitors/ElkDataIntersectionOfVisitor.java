
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataIntersectionOf;

/**
 * Visitor pattern interface for instances of {@link ElkDataIntersectionOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataIntersectionOfVisitor<O> {

	public O visit(ElkDataIntersectionOf expression);

}
