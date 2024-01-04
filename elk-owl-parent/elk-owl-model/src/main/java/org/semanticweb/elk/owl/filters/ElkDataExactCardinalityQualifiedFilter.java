
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityQualifiedVisitor;

/**
 * A filter producing objects in {@link ElkDataExactCardinalityQualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataExactCardinalityQualifiedFilter
		extends
		ElkDataExactCardinalityQualifiedVisitor<ElkDataExactCardinalityQualified> {

	// nothing else

}