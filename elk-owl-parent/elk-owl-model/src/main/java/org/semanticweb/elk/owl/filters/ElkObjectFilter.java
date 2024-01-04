
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * A filter producing objects in {@link ElkObject} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectFilter extends ElkAxiomFilter,
		ElkClassExpressionFilter, ElkSubObjectPropertyExpressionFilter,
		ElkDataPropertyExpressionFilter, ElkIndividualFilter, ElkLiteralFilter,
		ElkEntityFilter, ElkDataRangeFilter, ElkFacetRestrictionFilter,
		ElkAnnotationFilter, ElkAnnotationSubjectFilter,
		ElkAnnotationValueFilter {

	// combined visitor
}
