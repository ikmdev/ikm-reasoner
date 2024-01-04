
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkNegativeObjectPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;

/**
 * Implements the {@link ElkNegativeObjectPropertyAssertionAxiom} interface by
 * wrapping instances of {@link OWLNegativeObjectPropertyAssertionAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkNegativeObjectPropertyAssertionAxiomWrap<T extends OWLNegativeObjectPropertyAssertionAxiom>
		extends ElkAssertionAxiomWrap<T> implements
		ElkNegativeObjectPropertyAssertionAxiom {

	public ElkNegativeObjectPropertyAssertionAxiomWrap(
			T owlNegativeObjectPropertyAssertionAxiom) {
		super(owlNegativeObjectPropertyAssertionAxiom);
	}

	@Override
	public ElkIndividual getObject() {
		return converter.convert(getSubject(owlObject));
	}

	@Override
	public ElkIndividual getSubject() {
		return converter.convert(getObject(owlObject));
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkNegativeObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkNegativeObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkNegativeObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(
			ElkNegativeObjectPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
