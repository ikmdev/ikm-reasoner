
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinality;



/**
 * Visitor pattern interface for instances of {@link ElkObjectMaxCardinality}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectMaxCardinalityVisitor<O> extends
		ElkObjectMaxCardinalityQualifiedVisitor<O>,
		ElkObjectMaxCardinalityUnqualifiedVisitor<O> {

	// combined visitor

}
