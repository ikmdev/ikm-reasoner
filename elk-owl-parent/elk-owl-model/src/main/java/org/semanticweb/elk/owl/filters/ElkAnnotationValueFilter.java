
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationValue;

/**
 * A filter producing objects in {@link ElkAnnotationValue} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnnotationValueFilter extends ElkIriFilter,
		ElkLiteralFilter, ElkAnonymousIndividualFilter {

	// combined visitor

}
