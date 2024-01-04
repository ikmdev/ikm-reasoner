
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyVisitor;

/**
 * A filter producing objects in {@link ElkDataProperty} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataPropertyFilter extends
		ElkDataPropertyVisitor<ElkDataProperty> {

	// nothing else

}