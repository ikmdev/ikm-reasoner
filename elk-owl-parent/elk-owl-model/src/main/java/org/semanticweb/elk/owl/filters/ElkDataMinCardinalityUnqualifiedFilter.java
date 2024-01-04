
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityUnqualified;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityUnqualifiedVisitor;

/**
 * A filter producing objects in {@link ElkDataMinCardinalityUnqualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataMinCardinalityUnqualifiedFilter
		extends
		ElkDataMinCardinalityUnqualifiedVisitor<ElkDataMinCardinalityUnqualified> {

	// nothing else

}