
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;

/**
 * Visitor pattern interface for instances of {@link ElkDataRange}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataRangeVisitor<O> extends ElkDataComplementOfVisitor<O>,
		ElkDataIntersectionOfVisitor<O>, ElkDataOneOfVisitor<O>,
		ElkDatatypeVisitor<O>, ElkDatatypeRestrictionVisitor<O>,
		ElkDataUnionOfVisitor<O> {

	// combined visitor

}
