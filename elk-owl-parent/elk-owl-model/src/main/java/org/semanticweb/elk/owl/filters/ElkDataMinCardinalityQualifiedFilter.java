
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityQualifiedVisitor;

/**
 * A filter producing objects in {@link ElkDataMinCardinalityQualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataMinCardinalityQualifiedFilter extends
		ElkDataMinCardinalityQualifiedVisitor<ElkDataMinCardinalityQualified> {

	// nothing else

}