
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;
import org.semanticweb.elk.owl.visitors.ElkObjectHasSelfVisitor;

/**
 * A filter producing objects in {@link ElkObjectHasSelf} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectHasSelfFilter extends
		ElkObjectHasSelfVisitor<ElkObjectHasSelf> {

	// nothing else

}