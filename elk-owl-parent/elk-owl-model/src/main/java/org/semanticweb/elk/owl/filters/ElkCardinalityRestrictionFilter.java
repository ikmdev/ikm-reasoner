
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkCardinalityRestriction;

/**
 * A filter producing objects in {@link ElkCardinalityRestriction} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkCardinalityRestrictionFilter extends
		ElkCardinalityRestrictionQualifiedFilter,
		ElkDataExactCardinalityUnqualifiedFilter,
		ElkDataMaxCardinalityUnqualifiedFilter,
		ElkDataMinCardinalityUnqualifiedFilter,
		ElkObjectExactCardinalityUnqualifiedFilter,
		ElkObjectMaxCardinalityUnqualifiedFilter,
		ElkObjectMinCardinalityUnqualifiedFilter {

	// combined visitor
}
