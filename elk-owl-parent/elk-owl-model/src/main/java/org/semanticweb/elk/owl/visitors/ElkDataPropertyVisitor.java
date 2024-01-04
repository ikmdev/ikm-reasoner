 
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataProperty;

/**
 * Visitor pattern interface for instances of {@link ElkDataProperty}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataPropertyVisitor<O> {

	public O visit(ElkDataProperty expression);

}
