
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataIntersectionOf;
import org.semanticweb.elk.owl.visitors.ElkDataIntersectionOfVisitor;

/**
 * A filter producing objects in {@link ElkDataIntersectionOf} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataIntersectionOfFilter extends
		ElkDataIntersectionOfVisitor<ElkDataIntersectionOf> {

	// nothing else

}