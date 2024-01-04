
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyDomainAxiom;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyDomainAxiomVisitor;

/**
 * A filter producing objects in {@link ElkAnnotationPropertyDomainAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnnotationPropertyDomainAxiomFilter
		extends
		ElkAnnotationPropertyDomainAxiomVisitor<ElkAnnotationPropertyDomainAxiom> {

	// nothing else

}
