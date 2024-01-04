
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;

/**
 * Visitor pattern interface for instances of {@link ElkObjectSomeValuesFrom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectSomeValuesFromVisitor<O> {

	public O visit(ElkObjectSomeValuesFrom expression);

}
