
/**
 * @author Yevgeny Kazakov, Jul 3, 2011
 */
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * A filter producing objects in {@link ElkEntity} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkEntityFilter extends ElkAnnotationPropertyFilter,
		ElkClassFilter, ElkDataPropertyFilter, ElkDatatypeFilter,
		ElkNamedIndividualFilter, ElkObjectPropertyFilter {

	// combined visitor

}
