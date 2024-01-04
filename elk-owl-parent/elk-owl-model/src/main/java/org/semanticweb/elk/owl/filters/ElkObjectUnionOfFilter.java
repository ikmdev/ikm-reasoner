
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;
import org.semanticweb.elk.owl.visitors.ElkObjectUnionOfVisitor;

/**
 * A filter producing objects in {@link ElkObjectUnionOf} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectUnionOfFilter extends
		ElkObjectUnionOfVisitor<ElkObjectUnionOf> {

	// nothing else

}