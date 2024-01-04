
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;

/**
 * Visitor pattern interface for instances of {@link ElkDataHasValue}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataHasValueVisitor<O> {

	public O visit(ElkDataHasValue expression);

}
