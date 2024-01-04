
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkFacetRestriction;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkFacetRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Implementation of {@link ElkFacetRestriction}
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *
 */
public class ElkFacetRestrictionImpl implements ElkFacetRestriction {

	private final ElkIri facetURI_;
	private final ElkLiteral literal_;

	ElkFacetRestrictionImpl(ElkIri iri, ElkLiteral literal) {
		this.facetURI_ = iri;
		this.literal_ = literal;
	}

	@Override
	public ElkIri getConstrainingFacet() {
		return facetURI_;
	}

	@Override
	public ElkLiteral getRestrictionValue() {
		return literal_;
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
