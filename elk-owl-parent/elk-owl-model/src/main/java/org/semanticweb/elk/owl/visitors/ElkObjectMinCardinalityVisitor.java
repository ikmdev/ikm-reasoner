
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinality;



/**
 * Visitor pattern interface for instances of {@link ElkObjectMinCardinality}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectMinCardinalityVisitor<O> extends
		ElkObjectMinCardinalityQualifiedVisitor<O>,
		ElkObjectMinCardinalityUnqualifiedVisitor<O> {

	// combined visitor

}
