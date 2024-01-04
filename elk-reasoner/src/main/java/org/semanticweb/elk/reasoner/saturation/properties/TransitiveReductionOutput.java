
package org.semanticweb.elk.reasoner.saturation.properties;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * An result of transitive reduction.
 * 
 * @author Peter Skocovsky
 * 
 * @param <E>
 *            The type of elements whose relation was transitively reduced.
 */
public interface TransitiveReductionOutput<E extends ElkEntity> {

	void accept(TransitiveReductionOutputVisitor<E> visitor);

}
