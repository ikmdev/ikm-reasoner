
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.interfaces.ElkNegativeDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkNegativeDataPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;

/**
 * Implements the {@link ElkNegativeDataPropertyAssertionAxiom} interface by
 * wrapping instances of {@link OWLNegativeDataPropertyAssertionAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkNegativeDataPropertyAssertionAxiomWrap<T extends OWLNegativeDataPropertyAssertionAxiom>
		extends ElkAssertionAxiomWrap<T> implements
		ElkNegativeDataPropertyAssertionAxiom {

	public ElkNegativeDataPropertyAssertionAxiomWrap(
			T owlNegativeDataPropertyAssertionAxiom) {
		super(owlNegativeDataPropertyAssertionAxiom);
	}

	@Override
	public ElkIndividual getSubject() {
		return converter.convert(getSubject(owlObject));
	}

	@Override
	public ElkLiteral getObject() {
		return converter.convert(getObject(owlObject));
	}

	@Override
	public ElkDataPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkNegativeDataPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkNegativeDataPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkNegativeDataPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkNegativeDataPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}