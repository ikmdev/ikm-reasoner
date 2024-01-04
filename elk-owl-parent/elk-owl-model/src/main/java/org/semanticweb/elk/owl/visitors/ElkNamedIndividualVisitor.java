 
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;

/**
 * Visitor pattern interface for instances of {@link ElkNamedIndividual}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkNamedIndividualVisitor<O> {

	O visit(ElkNamedIndividual expression);

}
