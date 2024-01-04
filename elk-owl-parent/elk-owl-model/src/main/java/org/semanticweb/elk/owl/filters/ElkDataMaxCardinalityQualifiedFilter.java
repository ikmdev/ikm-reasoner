
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityQualifiedVisitor;

/**
 * A filter producing objects in {@link ElkDataMaxCardinalityQualified} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataMaxCardinalityQualifiedFilter extends
		ElkDataMaxCardinalityQualifiedVisitor<ElkDataMaxCardinalityQualified> {

	// nothing else

}