
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationAxiom;

/**
 * A filter producing objects in {@link ElkAnnotationAxiom} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnnotationAxiomFilter extends
		ElkAnnotationAssertionAxiomFilter,
		ElkAnnotationPropertyDomainAxiomFilter,
		ElkAnnotationPropertyRangeAxiomFilter,
		ElkSubAnnotationPropertyOfAxiomFilter {

	// combined visitor

}
