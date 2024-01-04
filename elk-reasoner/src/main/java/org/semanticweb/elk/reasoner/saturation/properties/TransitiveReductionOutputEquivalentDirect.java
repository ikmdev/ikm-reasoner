
package org.semanticweb.elk.reasoner.saturation.properties;

import java.util.Collection;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * The result of transitive reduction that contains equivalent elements and
 * collections of equivalent elements that are directly related to them.
 * 
 * @author Peter Skocovsky
 * 
 * @param <E>
 *            The type of elements whose relation was transitively reduced.
 */
public interface TransitiveReductionOutputEquivalentDirect<E extends ElkEntity>
		extends TransitiveReductionOutputEquivalent<E> {

	Iterable<? extends Collection<? extends E>> getDirectlyRelated();

}
