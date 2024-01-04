
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectInverseOf;
import org.semanticweb.elk.owl.visitors.ElkObjectInverseOfVisitor;

/**
 * A filter producing objects in {@link ElkObjectInverseOf} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectInverseOfFilter extends
		ElkObjectInverseOfVisitor<ElkObjectInverseOf> {

	// nothing else

}