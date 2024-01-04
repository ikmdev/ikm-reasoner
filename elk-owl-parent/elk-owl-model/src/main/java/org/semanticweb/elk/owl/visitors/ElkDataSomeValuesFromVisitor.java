
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataSomeValuesFrom;

/**
 * Visitor pattern interface for instances of {@link ElkDataSomeValuesFrom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataSomeValuesFromVisitor<O> {

	public O visit(ElkDataSomeValuesFrom expression);

}
