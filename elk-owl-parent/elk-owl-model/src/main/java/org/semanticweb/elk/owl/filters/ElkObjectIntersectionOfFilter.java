
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.owl.visitors.ElkObjectIntersectionOfVisitor;

/**
 * A filter producing objects in {@link ElkObjectIntersectionOf} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectIntersectionOfFilter extends
		ElkObjectIntersectionOfVisitor<ElkObjectIntersectionOf> {

	// nothing else

}