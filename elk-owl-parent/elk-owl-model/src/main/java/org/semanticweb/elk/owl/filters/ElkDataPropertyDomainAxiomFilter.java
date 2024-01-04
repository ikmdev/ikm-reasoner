
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataPropertyDomainAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyDomainAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDataPropertyDomainAxiom} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataPropertyDomainAxiomFilter extends
		ElkDataPropertyDomainAxiomVisitor<ElkDataPropertyDomainAxiom> {

	// nothing else

}