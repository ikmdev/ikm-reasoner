 
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkFunctionalObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkFunctionalObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;

/**
 * Implements the {@link ElkFunctionalObjectPropertyAxiom} interface by wrapping
 * instances of {@link OWLFunctionalObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkFunctionalObjectPropertyAxiomWrap<T extends OWLFunctionalObjectPropertyAxiom>
		extends ElkObjectPropertyAxiomWrap<T>
		implements ElkFunctionalObjectPropertyAxiom {

	public ElkFunctionalObjectPropertyAxiomWrap(
			T owlFunctionalObjectPropertyAxiom) {
		super(owlFunctionalObjectPropertyAxiom);
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
		return accept((ElkFunctionalObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkFunctionalObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
