
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyOfAxiomVisitor;

/**
 * A filter producing objects in {@link ElkSubObjectPropertyOfAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkSubObjectPropertyOfAxiomFilter extends
		ElkSubObjectPropertyOfAxiomVisitor<ElkSubObjectPropertyOfAxiom> {

	// nothing else

}