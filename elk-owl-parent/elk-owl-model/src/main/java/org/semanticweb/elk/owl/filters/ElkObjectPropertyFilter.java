
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyVisitor;

/**
 * A filter producing objects in {@link ElkObjectProperty} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectPropertyFilter extends
		ElkObjectPropertyVisitor<ElkObjectProperty> {

	// nothing else

}