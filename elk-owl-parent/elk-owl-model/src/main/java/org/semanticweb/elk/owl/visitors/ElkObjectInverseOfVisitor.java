
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectInverseOf;

/**
 * Visitor pattern interface for instances of {@link ElkObjectInverseOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectInverseOfVisitor<O> {

	public O visit(ElkObjectInverseOf expression);

}
