
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinality;



/**
 * Visitor pattern interface for instances of {@link ElkObjectExactCardinality}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectExactCardinalityVisitor<O> extends
		ElkObjectExactCardinalityQualifiedVisitor<O>,
		ElkObjectExactCardinalityUnqualifiedVisitor<O> {

	// combined visitor

}
