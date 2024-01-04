
package org.semanticweb.elk.reasoner.saturation.properties;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * The result of transitive reduction that contains an element that is related
 * to every element before the relation is transitive reduced (equivalent to the
 * bottom).
 * 
 * @author Peter Skocovsky
 * 
 * @param <E>
 *            The type of elements whose relation was transitively reduced.
 */
public interface TransitiveReductionOutputExtreme<E extends ElkEntity>
		extends TransitiveReductionOutput<E> {

	E getExtremeMember();

}
