
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;

/**
 * Visitor interface for {@link ElkIndividual}.
 * 
 * @author Markus Kroetzsch
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkIndividualVisitor<O> extends
		ElkAnonymousIndividualVisitor<O>, ElkNamedIndividualVisitor<O> {

	// combined visitor

}
