
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

/**
 * Visitor pattern interface for instances of {@link ElkObjectProperty}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectPropertyVisitor<O> {

	public O visit(ElkObjectProperty expression);

}
