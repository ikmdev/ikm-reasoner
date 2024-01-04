
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.iris.ElkAbbreviatedIri;
import org.semanticweb.elk.owl.visitors.ElkAbbreviatedIriVisitor;

/**
 * A filter producing objects in {@link ElkAbbreviatedIri} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAbbreviatedIriFilter extends
		ElkAbbreviatedIriVisitor<ElkAbbreviatedIri> {

	// nothing else

}
