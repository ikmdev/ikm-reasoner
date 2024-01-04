
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectHasValue;

/**
 * Visitor pattern interface for instances of {@link ElkObjectHasValue}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectHasValueVisitor<O> {

	public O visit(ElkObjectHasValue expression);

}
