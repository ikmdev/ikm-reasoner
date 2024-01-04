
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkInverseObjectPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkInverseObjectPropertiesAxiomVisitor;

/**
 * A filter producing objects in {@link ElkInverseObjectPropertiesAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkInverseObjectPropertiesAxiomFilter extends
		ElkInverseObjectPropertiesAxiomVisitor<ElkInverseObjectPropertiesAxiom> {

	// nothing else

}