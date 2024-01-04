
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnnotation;
import org.semanticweb.elk.owl.visitors.ElkAnnotationVisitor;

/**
 * A filter producing objects in {@link ElkAnnotation} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnnotationFilter extends
		ElkAnnotationVisitor<ElkAnnotation> {

	// nothing else

}
