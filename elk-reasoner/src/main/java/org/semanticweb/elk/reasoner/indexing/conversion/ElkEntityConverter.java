
package org.semanticweb.elk.reasoner.indexing.conversion;

import org.semanticweb.elk.owl.interfaces.ElkEntity;



import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEntity;

/**
 * Converts an {@link ElkEntity} to the corresponding
 * {@link ModifiableIndexedEntity}
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ElkEntityConverter extends
		ElkEntityVisitor<ModifiableIndexedEntity> {

	// combined interface
}
