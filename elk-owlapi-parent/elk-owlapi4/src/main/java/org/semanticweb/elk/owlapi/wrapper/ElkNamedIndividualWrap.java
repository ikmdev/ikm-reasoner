
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkNamedIndividualVisitor;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 * Implements the {@link ElkNamedIndividual} interface by wrapping instances of
 * {@link OWLNamedIndividual}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkNamedIndividualWrap<T extends OWLNamedIndividual> extends
		ElkEntityWrap<T> implements ElkNamedIndividual {

	public ElkNamedIndividualWrap(T owlNamedIndividual) {
		super(owlNamedIndividual);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.NAMED_INDIVIDUAL;
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
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

}
