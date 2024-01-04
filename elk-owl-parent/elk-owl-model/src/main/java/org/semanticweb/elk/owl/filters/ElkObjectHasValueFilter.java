
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectHasValue;
import org.semanticweb.elk.owl.visitors.ElkObjectHasValueVisitor;

/**
 * A filter producing objects in {@link ElkObjectHasValue} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectHasValueFilter extends
		ElkObjectHasValueVisitor<ElkObjectHasValue> {

	// nothing else

}