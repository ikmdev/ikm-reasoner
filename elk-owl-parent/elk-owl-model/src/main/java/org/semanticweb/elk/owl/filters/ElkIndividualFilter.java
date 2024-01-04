
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;

/**
 * A filter producing objects in {@link ElkIndividual} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkIndividualFilter extends ElkAnonymousIndividualFilter,
		ElkNamedIndividualFilter {

	// combined visitor

}
