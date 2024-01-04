
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRestrictionQualified;



/**
 * A filter producing objects in {@link ElkPropertyRestrictionQualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkPropertyRestrictionQualifiedFilter extends
		ElkCardinalityRestrictionQualifiedFilter, ElkDataHasValueFilter,
		ElkObjectAllValuesFromFilter, ElkObjectHasValueFilter,
		ElkObjectSomeValuesFromFilter {

	// combined visitor

}
