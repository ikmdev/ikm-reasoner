
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyAxiom;

/**
 * Implements the {@link ElkDataPropertyAxiom} interface by wrapping instances
 * of {@link OWLDataPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkDataPropertyAxiomWrap<T extends OWLDataPropertyAxiom>
		extends ElkAxiomWrap<T> implements ElkDataPropertyAxiom {

	public ElkDataPropertyAxiomWrap(T owlDataPropertyAxiom) {
		super(owlDataPropertyAxiom);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyAxiomVisitor<O>) visitor);
	}
}