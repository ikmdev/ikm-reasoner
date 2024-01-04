
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

/**
 * A filter producing objects in {@link ElkClassExpression} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkClassExpressionFilter extends ElkClassFilter,
		ElkDataPropertyListRestrictionQualifiedFilter,
		ElkObjectComplementOfFilter, ElkObjectIntersectionOfFilter,
		ElkObjectOneOfFilter, ElkObjectUnionOfFilter,
		ElkPropertyRestrictionFilter {

	// combined visitor
}
