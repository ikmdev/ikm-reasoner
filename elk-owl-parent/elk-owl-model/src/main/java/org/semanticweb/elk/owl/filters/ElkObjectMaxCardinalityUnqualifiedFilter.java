
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityUnqualified;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityUnqualifiedVisitor;

/**
 * A filter producing objects in {@link ElkObjectMaxCardinalityUnqualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectMaxCardinalityUnqualifiedFilter
		extends
		ElkObjectMaxCardinalityUnqualifiedVisitor<ElkObjectMaxCardinalityUnqualified> {

	// nothing else

}