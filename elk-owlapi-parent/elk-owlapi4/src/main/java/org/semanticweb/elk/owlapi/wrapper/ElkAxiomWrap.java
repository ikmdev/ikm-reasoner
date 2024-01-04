
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLAxiom;

/**
 * Implements the {@link ElkAxiom} interface by wrapping instances of
 * {@link OWLAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkAxiomWrap<T extends OWLAxiom> extends ElkObjectWrap<T>
		implements ElkAxiom {

	public ElkAxiomWrap(T owlAxiom) {
		super(owlAxiom);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkAxiomVisitor<O>) visitor);
	}

	public T getOWLAxiom() {
		return owlObject;
	}
}
