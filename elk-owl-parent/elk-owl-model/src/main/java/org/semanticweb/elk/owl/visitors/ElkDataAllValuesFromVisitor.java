
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataAllValuesFrom;

/**
 * Visitor pattern interface for instances of {@link ElkDataAllValuesFrom}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataAllValuesFromVisitor<O> {

	public O visit(ElkDataAllValuesFrom expression);

}
