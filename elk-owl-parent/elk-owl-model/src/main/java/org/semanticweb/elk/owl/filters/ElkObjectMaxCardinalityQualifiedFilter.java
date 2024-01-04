
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityQualifiedVisitor;

/**
 * A filter producing objects in {@link ElkObjectMaxCardinalityQualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectMaxCardinalityQualifiedFilter
		extends
		ElkObjectMaxCardinalityQualifiedVisitor<ElkObjectMaxCardinalityQualified> {

	// nothing else

}