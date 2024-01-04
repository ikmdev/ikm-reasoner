
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;

/**
 * Implements the {@link ElkDataPropertyAssertionAxiom} interface by wrapping
 * instances of {@link OWLDataPropertyAssertionAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataPropertyAssertionAxiomWrap<T extends OWLDataPropertyAssertionAxiom>
		extends ElkAssertionAxiomWrap<T> implements
		ElkDataPropertyAssertionAxiom {

	public ElkDataPropertyAssertionAxiomWrap(T owlObjectPropertyAssertionAxiom) {
		super(owlObjectPropertyAssertionAxiom);
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
	public <O> O accept(ElkDataPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyAssertionAxiomVisitor<O>) visitor);
	}
}
