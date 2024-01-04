
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkNamedIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Named_Individuals">Named Individuals<a> in
 * the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public class ElkNamedIndividualImpl extends ElkIriObject implements ElkEntity,
		ElkNamedIndividual {

	ElkNamedIndividualImpl(ElkIri iri) {
		super(iri);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.NAMED_INDIVIDUAL;
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkNamedIndividualVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkIndividualVisitor<O> visitor) {
		return accept((ElkNamedIndividualVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkNamedIndividualVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return accept((ElkNamedIndividualVisitor<O>) visitor);
	}

}
