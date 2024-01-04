
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinality;



/**
 * Visitor pattern interface for instances of {@link ElkDataExactCardinality}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataExactCardinalityVisitor<O> extends
		ElkDataExactCardinalityQualifiedVisitor<O>,
		ElkDataExactCardinalityUnqualifiedVisitor<O> {

	// combined visitor

}
