
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassAxiom;

/**
 * Implements the {@link ElkClassAxiom} interface by wrapping instances of
 * {@link OWLClassAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkClassAxiomWrap<T extends OWLClassAxiom> extends
		ElkAxiomWrap<T> implements ElkClassAxiom {

	public ElkClassAxiomWrap(T owlClassAxiom) {
		super(owlClassAxiom);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkClassAxiomVisitor<O>) visitor);
	}

}
