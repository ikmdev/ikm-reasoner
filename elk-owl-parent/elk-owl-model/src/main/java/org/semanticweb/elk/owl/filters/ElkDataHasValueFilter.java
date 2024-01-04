
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.visitors.ElkDataHasValueVisitor;

/**
 * A filter producing objects in {@link ElkDataHasValue} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataHasValueFilter extends
		ElkDataHasValueVisitor<ElkDataHasValue> {

	// nothing else

}