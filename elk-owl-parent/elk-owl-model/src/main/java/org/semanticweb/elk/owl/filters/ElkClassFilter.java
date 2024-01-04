
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.visitors.ElkClassVisitor;

/**
 * A filter producing objects in {@link ElkClass} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkClassFilter extends ElkClassVisitor<ElkClass> {

	// nothing else

}
