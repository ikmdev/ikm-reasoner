
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataOneOf;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkDataOneOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.owlapi.model.OWLDataOneOf;
import org.semanticweb.owlapi.model.OWLLiteral;

/**
 * Implements the {@link ElkDataOneOf} interface by wrapping instances of
 * {@link OWLDataOneOf}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataOneOfWrap<T extends OWLDataOneOf> extends
		ElkDataRangeWrap<T> implements ElkDataOneOf {

	public ElkDataOneOfWrap(T refOWLDataOneOf) {
		super(refOWLDataOneOf);
	}

	@Override
	public List<? extends ElkLiteral> getLiterals() {
		List<ElkLiteral> result = new ArrayList<ElkLiteral>();
		for (OWLLiteral ltr : this.owlObject.getValues()) {
			result.add(converter.convert(ltr));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataOneOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataOneOfVisitor<O> visitor) {
		return visitor.visit(this);
	}
}