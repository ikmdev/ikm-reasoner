
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRestriction;



/**
 * A filter producing objects in {@link ElkPropertyRestriction} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkPropertyRestrictionFilter extends
		ElkCardinalityRestrictionFilter, ElkObjectHasSelfFilter,
		ElkPropertyRestrictionQualifiedFilter {

	// combined visitor
}
