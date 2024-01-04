
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;

/**
 * Visitor pattern interface for instances of {@link ElkObjectOneOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectOneOfVisitor<O> {

	public O visit(ElkObjectOneOf expression);

}
