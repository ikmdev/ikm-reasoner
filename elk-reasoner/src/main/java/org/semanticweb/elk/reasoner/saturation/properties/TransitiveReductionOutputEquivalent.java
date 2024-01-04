
package org.semanticweb.elk.reasoner.saturation.properties;

import java.util.Collection;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * The result of transitive reduction that contains equivalent elements.
 * 
 * @author Peter Skocovsky
 * 
 * @param <E>
 *            The type of elements whose relation was transitively reduced.
 */
public interface TransitiveReductionOutputEquivalent<E extends ElkEntity>
		extends TransitiveReductionOutput<E> {

	Collection<? extends E> getEquivalent();

}
