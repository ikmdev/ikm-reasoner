
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinality;



/**
 * A filter producing objects in {@link ElkObjectExactCardinality} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectExactCardinalityFilter extends
		ElkObjectExactCardinalityQualifiedFilter,
		ElkObjectExactCardinalityUnqualifiedFilter {

	// combined visitor

}
