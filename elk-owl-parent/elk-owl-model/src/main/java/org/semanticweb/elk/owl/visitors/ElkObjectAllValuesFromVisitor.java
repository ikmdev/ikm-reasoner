
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectAllValuesFrom;

/**
 * Visitor pattern interface for instances of {@link ElkObjectAllValuesFrom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectAllValuesFromVisitor<O> {

	public O visit(ElkObjectAllValuesFrom expression);

}
