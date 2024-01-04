
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.interfaces.ElkDataUnionOf;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataUnionOfVisitor;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDataUnionOf;

/**
 * Implements the {@link ElkDataUnionOf} interface by wrapping instances of
 * {@link OWLDataUnionOf}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataUnionOfWrap<T extends OWLDataUnionOf> extends
		ElkDataRangeWrap<T> implements ElkDataUnionOf {

	public ElkDataUnionOfWrap(T owlDataUnionOf) {
		super(owlDataUnionOf);
	}

	@Override
	public List<? extends ElkDataRange> getDataRanges() {
		List<ElkDataRange> result = new ArrayList<ElkDataRange>();
		for (OWLDataRange ran : this.owlObject.getOperands()) {
			result.add(converter.convert(ran));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataUnionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataUnionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}
}