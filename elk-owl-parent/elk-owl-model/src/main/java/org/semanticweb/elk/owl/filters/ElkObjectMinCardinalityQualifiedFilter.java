
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityQualifiedVisitor;

/**
 * A filter producing objects in {@link ElkObjectMinCardinalityQualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectMinCardinalityQualifiedFilter
		extends
		ElkObjectMinCardinalityQualifiedVisitor<ElkObjectMinCardinalityQualified> {

	// nothing else

}