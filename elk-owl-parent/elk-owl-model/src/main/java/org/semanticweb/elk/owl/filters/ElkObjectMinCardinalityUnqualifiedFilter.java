
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityUnqualified;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityUnqualifiedVisitor;

/**
 * A filter producing objects in {@link ElkObjectMinCardinalityUnqualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectMinCardinalityUnqualifiedFilter
		extends
		ElkObjectMinCardinalityUnqualifiedVisitor<ElkObjectMinCardinalityUnqualified> {

	// nothing else

}