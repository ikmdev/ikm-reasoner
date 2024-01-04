
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinality;



/**
 * A filter producing objects in {@link ElkObjectMinCardinality} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectMinCardinalityFilter extends
		ElkObjectMinCardinalityQualifiedFilter,
		ElkObjectMinCardinalityUnqualifiedFilter {

	// combined visitor

}
