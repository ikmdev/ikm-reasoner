
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;

/**
 * A filter producing objects in {@link ElkObjectPropertyExpression} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectPropertyExpressionFilter extends
		ElkObjectInverseOfFilter, ElkObjectPropertyFilter {

	// combined visitor

}
