
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkCardinalityRestrictionQualified;



/**
 * Visitor pattern interface for instances of
 * {@link ElkCardinalityRestrictionQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkCardinalityRestrictionQualifiedVisitor<O> extends
		ElkDataExactCardinalityQualifiedVisitor<O>,
		ElkDataMaxCardinalityQualifiedVisitor<O>,
		ElkDataMinCardinalityQualifiedVisitor<O>,
		ElkObjectExactCardinalityQualifiedVisitor<O>,
		ElkObjectMaxCardinalityQualifiedVisitor<O>,
		ElkObjectMinCardinalityQualifiedVisitor<O> {

	// combined visitor

}
