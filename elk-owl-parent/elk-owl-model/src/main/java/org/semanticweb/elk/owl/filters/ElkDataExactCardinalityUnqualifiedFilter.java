
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityUnqualified;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityUnqualifiedVisitor;

/**
 * A filter producing objects in {@link ElkDataExactCardinalityUnqualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataExactCardinalityUnqualifiedFilter
		extends
		ElkDataExactCardinalityUnqualifiedVisitor<ElkDataExactCardinalityUnqualified> {

	// nothing else

}