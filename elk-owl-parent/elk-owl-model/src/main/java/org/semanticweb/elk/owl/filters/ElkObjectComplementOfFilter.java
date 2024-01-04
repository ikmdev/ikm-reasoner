
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;
import org.semanticweb.elk.owl.visitors.ElkObjectComplementOfVisitor;

/**
 * A filter producing objects in {@link ElkObjectComplementOf} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectComplementOfFilter extends
		ElkObjectComplementOfVisitor<ElkObjectComplementOf> {

	// nothing else

}