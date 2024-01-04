
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinality;



/**
 * A filter producing objects in {@link ElkObjectMaxCardinality} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectMaxCardinalityFilter extends
		ElkObjectMaxCardinalityQualifiedFilter,
		ElkObjectMaxCardinalityUnqualifiedFilter {

	// combined visitor

}
