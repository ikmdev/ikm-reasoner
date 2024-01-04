
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnonymousIndividual;
import org.semanticweb.elk.owl.visitors.ElkAnonymousIndividualVisitor;

/**
 * A filter producing objects in {@link ElkAnonymousIndividual} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnonymousIndividualFilter extends
		ElkAnonymousIndividualVisitor<ElkAnonymousIndividual> {

	// nothing else

}
