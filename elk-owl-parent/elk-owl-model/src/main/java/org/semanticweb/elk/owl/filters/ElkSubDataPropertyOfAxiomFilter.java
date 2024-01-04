
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkSubDataPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkSubDataPropertyOfAxiomVisitor;

/**
 * A filter producing objects in {@link ElkSubDataPropertyOfAxiom} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkSubDataPropertyOfAxiomFilter extends
		ElkSubDataPropertyOfAxiomVisitor<ElkSubDataPropertyOfAxiom> {

	// nothing else

}