
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinality;



/**
 * Visitor pattern interface for instances of {@link ElkDataMaxCardinality}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataMaxCardinalityVisitor<O> extends
		ElkDataMaxCardinalityQualifiedVisitor<O>,
		ElkDataMaxCardinalityUnqualifiedVisitor<O> {

	// combined visitor

}
