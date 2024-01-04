
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLDataRange;

/**
 * Implements the {@link ElkDataRange} interface by wrapping instances of
 * {@link OWLDataRange}
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkDataRangeWrap<T extends OWLDataRange> extends
		ElkObjectWrap<T> implements ElkDataRange {

	public ElkDataRangeWrap(T owlDataRange) {
		super(owlDataRange);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataRangeVisitor<O>) visitor);
	}
}