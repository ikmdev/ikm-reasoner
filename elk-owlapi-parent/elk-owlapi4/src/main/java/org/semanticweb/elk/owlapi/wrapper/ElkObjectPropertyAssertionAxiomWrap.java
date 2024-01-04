
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;

/**
 * Implements the {@link ElkObjectPropertyAssertionAxiom} interface by wrapping
 * instances of {@link OWLObjectPropertyAssertionAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectPropertyAssertionAxiomWrap<T extends OWLObjectPropertyAssertionAxiom>
		extends ElkAssertionAxiomWrap<T> implements
		ElkObjectPropertyAssertionAxiom {

	public ElkObjectPropertyAssertionAxiomWrap(T owlObjectPropertyAssertionAxiom) {
		super(owlObjectPropertyAssertionAxiom);
	}

	@Override
	public ElkIndividual getObject() {
		return converter.convert(getObject(owlObject));
	}

	@Override
	public ElkIndividual getSubject() {
		return converter.convert(getSubject(owlObject));
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
