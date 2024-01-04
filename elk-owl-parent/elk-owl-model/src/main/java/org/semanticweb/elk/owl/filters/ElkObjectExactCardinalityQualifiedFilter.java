 
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityQualifiedVisitor;

/**
 * A filter producing objects in {@link ElkObjectExactCardinalityQualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectExactCardinalityQualifiedFilter
		extends
		ElkObjectExactCardinalityQualifiedVisitor<ElkObjectExactCardinalityQualified> {

	// nothing else

}