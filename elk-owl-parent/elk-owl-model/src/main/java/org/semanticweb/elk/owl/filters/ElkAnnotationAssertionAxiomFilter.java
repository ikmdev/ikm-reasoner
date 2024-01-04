
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAssertionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkAnnotationAssertionAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAnnotationAssertionAxiomFilter extends
		ElkAnnotationAssertionAxiomVisitor<ElkAnnotationAssertionAxiom> {

	// nothing else

}
