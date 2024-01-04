
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkDatatypeRestriction;
import org.semanticweb.elk.owl.interfaces.ElkFacetRestriction;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkDatatypeRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.model.OWLFacetRestriction;

/**
 * Implements the {@link ElkDatatypeRestriction} interface by wrapping instances
 * of {@link OWLDatatypeRestriction}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDatatypeRestrictionWrap<T extends OWLDatatypeRestriction>
		extends ElkDataRangeWrap<T> implements ElkDatatypeRestriction {

	public ElkDatatypeRestrictionWrap(T owlDatatypeRestriction) {
		super(owlDatatypeRestriction);
	}

	@Override
	public ElkDatatype getDatatype() {
		return converter.convert(this.owlObject.getDatatype());
	}

	@Override
	public List<? extends ElkFacetRestriction> getFacetRestrictions() {
		List<ElkFacetRestriction> result = new ArrayList<ElkFacetRestriction>();
		for (OWLFacetRestriction frstr : this.owlObject.getFacetRestrictions()) {
			result.add(converter.convert(frstr));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDatatypeRestrictionVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDatatypeRestrictionVisitor<O> visitor) {
		return visitor.visit(this);
	}
}