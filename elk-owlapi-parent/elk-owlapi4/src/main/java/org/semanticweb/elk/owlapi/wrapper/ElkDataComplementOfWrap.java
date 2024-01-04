
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataComplementOf;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataComplementOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.owlapi.model.OWLDataComplementOf;

/**
 * Implements the {@link ElkDataComplementOf} interface by wrapping instances of
 * {@link OWLDataComplementOf}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataComplementOfWrap<T extends OWLDataComplementOf> extends
		ElkDataRangeWrap<T> implements ElkDataComplementOf {

	public ElkDataComplementOfWrap(T owlDataComplementOf) {
		super(owlDataComplementOf);
	}

	@Override
	public ElkDataRange getDataRange() {
		return converter.convert(this.owlObject.getDataRange());
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataComplementOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataComplementOfVisitor<O> visitor) {
		return visitor.visit(this);
	}
}