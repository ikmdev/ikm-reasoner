
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinality;



/**
 * A filter producing objects in {@link ElkDataMaxCardinality} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataMaxCardinalityFilter extends
		ElkDataMaxCardinalityQualifiedFilter,
		ElkDataMaxCardinalityUnqualifiedFilter {

	// combined visitor

}
