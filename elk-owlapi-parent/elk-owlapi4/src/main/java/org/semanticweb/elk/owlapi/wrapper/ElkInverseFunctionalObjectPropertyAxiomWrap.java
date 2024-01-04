
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkInverseFunctionalObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;

/**
 * Implements the {@link ElkInverseFunctionalObjectPropertyAxiom} interface by
 * wrapping instances of {@link OWLInverseFunctionalObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkInverseFunctionalObjectPropertyAxiomWrap<T extends OWLInverseFunctionalObjectPropertyAxiom>
		extends ElkObjectPropertyAxiomWrap<T>
		implements ElkInverseFunctionalObjectPropertyAxiom {

	public ElkInverseFunctionalObjectPropertyAxiomWrap(
			T owlInverseFunctionalObjectPropertyAxiom) {
		super(owlInverseFunctionalObjectPropertyAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept(
				(ElkInverseFunctionalObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(
			ElkInverseFunctionalObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
