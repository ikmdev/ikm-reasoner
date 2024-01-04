
package org.semanticweb.elk.reasoner.saturation.properties;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Visitor of {@link TransitiveReductionOutput}s.
 * 
 * @author Peter Skocovsky
 * 
 * @param <E> The type of elements whose relation was transitively reduced.
 */
public interface TransitiveReductionOutputVisitor<E extends ElkEntity> {

	void visit(TransitiveReductionOutputEquivalent<E> output);

	void visit(TransitiveReductionOutputEquivalentDirect<E> output);

	void visit(TransitiveReductionOutputExtreme<E> output);

}
