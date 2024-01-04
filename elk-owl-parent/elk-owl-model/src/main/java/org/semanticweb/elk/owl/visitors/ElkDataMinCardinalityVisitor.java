
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinality;



/**
 * Visitor pattern interface for instances of {@link ElkDataMinCardinality}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataMinCardinalityVisitor<O> extends
		ElkDataMinCardinalityQualifiedVisitor<O>,
		ElkDataMinCardinalityUnqualifiedVisitor<O> {

	// combined visitor

}
