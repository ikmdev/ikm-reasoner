
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinalityUnqualified;
import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityUnqualifiedVisitor;

/**
 * A filter producing objects in {@link ElkDataMaxCardinalityUnqualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataMaxCardinalityUnqualifiedFilter
		extends
		ElkDataMaxCardinalityUnqualifiedVisitor<ElkDataMaxCardinalityUnqualified> {

	// nothing else

}