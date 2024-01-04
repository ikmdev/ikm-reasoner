
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.visitors.ElkIndividualVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLIndividual;

/**
 * Implements the {@link ElkIndividual} interface by wrapping instances of
 * {@link OWLIndividual}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkIndividualWrap<T extends OWLIndividual> extends
		ElkObjectWrap<T> implements ElkIndividual {

	public ElkIndividualWrap(T owlIndividual) {
		super(owlIndividual);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkIndividualVisitor<O>) visitor);
	}
}
