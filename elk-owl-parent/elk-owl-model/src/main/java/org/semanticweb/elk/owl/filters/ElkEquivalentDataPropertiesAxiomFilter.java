
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkEquivalentDataPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkEquivalentDataPropertiesAxiomVisitor;

/**
 * A filter producing objects in {@link ElkEquivalentDataPropertiesAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkEquivalentDataPropertiesAxiomFilter
		extends
		ElkEquivalentDataPropertiesAxiomVisitor<ElkEquivalentDataPropertiesAxiom> {

	// nothing else

}