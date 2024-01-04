
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkFunctionalDataPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkFunctionalDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;

/**
 * Implements the {@link ElkFunctionalDataPropertyAxiom} interface by wrapping
 * instances of {@link OWLFunctionalDataPropertyAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkFunctionalDataPropertyAxiomWrap<T extends OWLFunctionalDataPropertyAxiom>
		extends ElkDataPropertyAxiomWrap<T>
		implements ElkFunctionalDataPropertyAxiom {

	public ElkFunctionalDataPropertyAxiomWrap(
			T owlFunctionalDataPropertyAxiom) {
		super(owlFunctionalDataPropertyAxiom);
	}

	@Override
	public ElkDataPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkFunctionalDataPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkFunctionalDataPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}