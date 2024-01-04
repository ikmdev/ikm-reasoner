
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyDomainAxiomVisitor;

/**
 * A filter producing objects in {@link ElkObjectPropertyDomainAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectPropertyDomainAxiomFilter extends
		ElkObjectPropertyDomainAxiomVisitor<ElkObjectPropertyDomainAxiom> {

	// nothing else

}