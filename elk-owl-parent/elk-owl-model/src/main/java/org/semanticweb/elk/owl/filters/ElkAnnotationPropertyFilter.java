
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyVisitor;

/**
 * A filter producing objects in {@link ElkAnnotationProperty} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnnotationPropertyFilter extends
		ElkAnnotationPropertyVisitor<ElkAnnotationProperty> {

	// nothing else

}
