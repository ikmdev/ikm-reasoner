
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkSubAnnotationPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkSubAnnotationPropertyOfAxiomVisitor;

/**
 * A filter producing objects in {@link ElkSubAnnotationPropertyOfAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkSubAnnotationPropertyOfAxiomFilter extends
		ElkSubAnnotationPropertyOfAxiomVisitor<ElkSubAnnotationPropertyOfAxiom> {

	// nothing else

}