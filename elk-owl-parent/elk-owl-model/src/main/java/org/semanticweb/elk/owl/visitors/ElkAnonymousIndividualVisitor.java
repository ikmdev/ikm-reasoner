 
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkAnonymousIndividual;

/**
 * Visitor pattern interface for instances of {@link ElkAnonymousIndividual}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkAnonymousIndividualVisitor<O> {

	O visit(ElkAnonymousIndividual expression);

}
