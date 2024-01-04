
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkFacetRestriction;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkFacetRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLFacetRestriction;

/**
 * Implements the {@link ElkFacetRestriction} interface by wrapping instances of
 * {@link OWLFacetRestriction}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkFacetRestrictionWrap<T extends OWLFacetRestriction> extends
		ElkObjectWrap<T> implements ElkFacetRestriction {

	public ElkFacetRestrictionWrap(T owlFacetRestriction) {
		super(owlFacetRestriction);
	}

	@Override
	public ElkIri getConstrainingFacet() {
		return converter.convert(this.owlObject.getFacet().getIRI());
	}

	@Override
	public ElkLiteral getRestrictionValue() {
		return converter.convert(this.owlObject.getFacetValue());
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkFacetRestrictionVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkFacetRestrictionVisitor<O> visitor) {
		return visitor.visit(this);
	}
}