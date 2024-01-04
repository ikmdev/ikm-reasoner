
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkCardinalityRestrictionQualified;

/**
 * Visitor pattern interface for instances of
 * {@link ElkCardinalityRestrictionQualified}.
 * 
 * @author "Yevgeny Kazakov" 
 */
public interface ElkCardinalityRestrictionQualifiedFilter extends
		ElkDataExactCardinalityQualifiedFilter,
		ElkDataMaxCardinalityQualifiedFilter,
		ElkDataMinCardinalityQualifiedFilter,
		ElkObjectExactCardinalityQualifiedFilter,
		ElkObjectMaxCardinalityQualifiedFilter,
		ElkObjectMinCardinalityQualifiedFilter {

	// combined visitor

}
