
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDatatypeRestriction;
import org.semanticweb.elk.owl.visitors.ElkDatatypeRestrictionVisitor;

/**
 * A filter producing objects in {@link ElkDatatypeRestriction} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDatatypeRestrictionFilter extends
		ElkDatatypeRestrictionVisitor<ElkDatatypeRestriction> {

	// nothing else

}