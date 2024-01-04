
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;

/**
 * Visitor pattern interface for instances of {@link ElkObjectComplementOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectComplementOfVisitor<O> {

	public O visit(ElkObjectComplementOf expression);

}
