
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinality;



/**
 * A filter producing objects in {@link ElkDataMinCardinality} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataMinCardinalityFilter extends
		ElkDataMinCardinalityQualifiedFilter,
		ElkDataMinCardinalityUnqualifiedFilter {

	// combined visitor

}
