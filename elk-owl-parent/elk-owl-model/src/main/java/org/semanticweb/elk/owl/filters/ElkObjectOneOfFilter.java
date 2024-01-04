
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;
import org.semanticweb.elk.owl.visitors.ElkObjectOneOfVisitor;

/**
 * A filter producing objects in {@link ElkObjectOneOf} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectOneOfFilter extends
		ElkObjectOneOfVisitor<ElkObjectOneOf> {

	// nothing else

}