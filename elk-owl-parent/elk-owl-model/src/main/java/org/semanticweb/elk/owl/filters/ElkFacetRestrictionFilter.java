
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkFacetRestriction;
import org.semanticweb.elk.owl.visitors.ElkFacetRestrictionVisitor;

/**
 * A filter producing objects in {@link ElkFacetRestriction} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkFacetRestrictionFilter extends
		ElkFacetRestrictionVisitor<ElkFacetRestriction> {

	// nothing else

}