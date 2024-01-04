
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkEquivalentObjectPropertiesAxiomVisitor;

/**
 * A filter producing objects in {@link ElkEquivalentObjectPropertiesAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkEquivalentObjectPropertiesAxiomFilter
		extends
		ElkEquivalentObjectPropertiesAxiomVisitor<ElkEquivalentObjectPropertiesAxiom> {

	// nothing else

}