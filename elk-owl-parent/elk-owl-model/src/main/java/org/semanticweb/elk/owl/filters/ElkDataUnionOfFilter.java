
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDataUnionOf;
import org.semanticweb.elk.owl.visitors.ElkDataUnionOfVisitor;

/**
 * A filter producing objects in {@link ElkDataUnionOf} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataUnionOfFilter extends
		ElkDataUnionOfVisitor<ElkDataUnionOf> {

	// nothing else

}