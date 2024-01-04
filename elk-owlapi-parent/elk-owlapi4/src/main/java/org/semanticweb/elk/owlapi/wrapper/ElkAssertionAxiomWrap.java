
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;

/**
 * Implements the {@link ElkAssertionAxiom} interface by wrapping instances of
 * {@link OWLIndividualAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkAssertionAxiomWrap<T extends OWLIndividualAxiom>
		extends ElkAxiomWrap<T> implements ElkAssertionAxiom {

	public ElkAssertionAxiomWrap(T owlIndividualAxiom) {
		super(owlIndividualAxiom);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkAssertionAxiomVisitor<O>) visitor);
	}

}
