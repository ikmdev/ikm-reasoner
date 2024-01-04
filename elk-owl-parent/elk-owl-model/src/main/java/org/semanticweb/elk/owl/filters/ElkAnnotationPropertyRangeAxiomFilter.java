
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyRangeAxiom;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyRangeAxiomVisitor;

/**
 * A filter producing objects in {@link ElkAnnotationPropertyRangeAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnnotationPropertyRangeAxiomFilter extends
		ElkAnnotationPropertyRangeAxiomVisitor<ElkAnnotationPropertyRangeAxiom> {

	// nothing else

}
