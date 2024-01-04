
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSymmetricObjectPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;

/**
 * Implements the {@link ElkSymmetricObjectPropertyAxiom} interface by wrapping
 * instances of {@link OWLSymmetricObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkSymmetricObjectPropertyAxiomWrap<T extends OWLSymmetricObjectPropertyAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkSymmetricObjectPropertyAxiom {

	public ElkSymmetricObjectPropertyAxiomWrap(T owlSymmetricObjectPropertyAxiom) {
		super(owlSymmetricObjectPropertyAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSymmetricObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSymmetricObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
