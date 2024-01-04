
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkCardinalityRestriction;



/**
 * Visitor pattern interface for instances of {@link ElkCardinalityRestriction}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkCardinalityRestrictionVisitor<O> extends
		ElkCardinalityRestrictionQualifiedVisitor<O>,
		ElkDataExactCardinalityUnqualifiedVisitor<O>,
		ElkDataMaxCardinalityUnqualifiedVisitor<O>,
		ElkDataMinCardinalityUnqualifiedVisitor<O>,
		ElkObjectExactCardinalityUnqualifiedVisitor<O>,
		ElkObjectMaxCardinalityUnqualifiedVisitor<O>,
		ElkObjectMinCardinalityUnqualifiedVisitor<O> {

	// combined visitor
}
