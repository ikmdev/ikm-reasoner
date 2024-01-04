
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;

/**
 * A filter producing objects in {@link ElkDataRange} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataRangeFilter extends ElkDataComplementOfFilter,
		ElkDataIntersectionOfFilter, ElkDataOneOfFilter, ElkDatatypeFilter,
		ElkDatatypeRestrictionFilter, ElkDataUnionOfFilter {

	// combined visitor

}
