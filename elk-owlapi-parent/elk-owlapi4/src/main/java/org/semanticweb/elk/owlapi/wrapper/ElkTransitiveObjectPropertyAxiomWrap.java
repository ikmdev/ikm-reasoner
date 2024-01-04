
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkTransitiveObjectPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;

/**
 * Implements the {@link ElkTransitiveObjectPropertyAxiom} interface by wrapping
 * instances of {@link OWLTransitiveObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkTransitiveObjectPropertyAxiomWrap<T extends OWLTransitiveObjectPropertyAxiom>
		extends ElkObjectPropertyAxiomWrap<T>
		implements ElkTransitiveObjectPropertyAxiom {

	public ElkTransitiveObjectPropertyAxiomWrap(
			T owlTransitiveObjectPropertyAxiom) {
		super(owlTransitiveObjectPropertyAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkTransitiveObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkTransitiveObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkTransitiveObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
