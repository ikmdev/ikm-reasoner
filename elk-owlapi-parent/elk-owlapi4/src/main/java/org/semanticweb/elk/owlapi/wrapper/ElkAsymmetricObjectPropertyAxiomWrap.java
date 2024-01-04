
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAsymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAsymmetricObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;

/**
 * Implements the {@link ElkAsymmetricObjectPropertyAxiom} interface by wrapping
 * instances of {@link OWLAsymmetricObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkAsymmetricObjectPropertyAxiomWrap<T extends OWLAsymmetricObjectPropertyAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkAsymmetricObjectPropertyAxiom {

	public ElkAsymmetricObjectPropertyAxiomWrap(
			T owlAsymmetricObjectPropertyAxiom) {
		super(owlAsymmetricObjectPropertyAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkAsymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkAsymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAsymmetricObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
