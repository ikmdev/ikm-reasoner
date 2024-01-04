
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataOneOf;

/**
 * Visitor pattern interface for instances of {@link ElkDataOneOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataOneOfVisitor<O> {

	public O visit(ElkDataOneOf expression);

}
