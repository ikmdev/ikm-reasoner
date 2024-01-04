
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinality;



/**
 * A filter producing objects in {@link ElkDataExactCardinality} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataExactCardinalityFilter extends
		ElkDataExactCardinalityQualifiedFilter,
		ElkDataExactCardinalityUnqualifiedFilter {

	// combined visitor

}
