
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;

/**
 * Implements the {@link ElkObjectPropertyAxiom} interface by wrapping instances of
 * {@link OWLObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkObjectPropertyAxiomWrap<T extends OWLObjectPropertyAxiom>
		extends ElkAxiomWrap<T> implements ElkObjectPropertyAxiom {

	public ElkObjectPropertyAxiomWrap(T owlObjectPropertyAxiom) {
		super(owlObjectPropertyAxiom);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyAxiomVisitor<O>) visitor);
	}

}
