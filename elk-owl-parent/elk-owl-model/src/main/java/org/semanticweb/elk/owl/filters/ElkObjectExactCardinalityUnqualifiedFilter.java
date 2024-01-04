
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityUnqualified;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityUnqualifiedVisitor;

/**
 * A filter producing objects in {@link ElkObjectExactCardinalityUnqualified}
 * from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectExactCardinalityUnqualifiedFilter
		extends
		ElkObjectExactCardinalityUnqualifiedVisitor<ElkObjectExactCardinalityUnqualified> {

	// nothing else

}