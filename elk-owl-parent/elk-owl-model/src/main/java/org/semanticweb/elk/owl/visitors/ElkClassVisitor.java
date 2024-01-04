
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkClass;

/**
 * Visitor pattern interface for instances of {@link ElkClass}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkClassVisitor<O> {

	public O visit(ElkClass expression);

}
