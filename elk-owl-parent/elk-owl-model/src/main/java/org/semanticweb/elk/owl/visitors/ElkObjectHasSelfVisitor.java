
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;

/**
 * Visitor pattern interface for instances of {@link ElkObjectHasSelf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectHasSelfVisitor<O> {

	public O visit(ElkObjectHasSelf expression);

}
