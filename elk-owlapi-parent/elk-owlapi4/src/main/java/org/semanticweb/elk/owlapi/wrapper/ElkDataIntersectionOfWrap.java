
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataIntersectionOf;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataIntersectionOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.owlapi.model.OWLDataIntersectionOf;
import org.semanticweb.owlapi.model.OWLDataRange;

/**
 * Implements the {@link ElkDataIntersectionOf} interface by wrapping instances
 * of {@link OWLDataIntersectionOf}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataIntersectionOfWrap<T extends OWLDataIntersectionOf> extends
		ElkDataRangeWrap<T> implements ElkDataIntersectionOf {

	public ElkDataIntersectionOfWrap(T owlDataIntersectionOf) {
		super(owlDataIntersectionOf);
	}

	@Override
	public List<? extends ElkDataRange> getDataRanges() {
		List<ElkDataRange> result = new ArrayList<ElkDataRange>();
		for (OWLDataRange dr : this.owlObject.getOperands()) {
			result.add(converter.convert(dr));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataIntersectionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataIntersectionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}
}