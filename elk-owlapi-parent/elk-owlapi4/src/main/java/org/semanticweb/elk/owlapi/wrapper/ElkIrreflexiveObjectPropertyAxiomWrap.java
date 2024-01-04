
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkIrreflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkIrreflexiveObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;

/**
 * Implements the {@link ElkIrreflexiveObjectPropertyAxiom} interface by
 * wrapping instances of {@link OWLIrreflexiveObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkIrreflexiveObjectPropertyAxiomWrap<T extends OWLIrreflexiveObjectPropertyAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkIrreflexiveObjectPropertyAxiom {

	public ElkIrreflexiveObjectPropertyAxiomWrap(
			T owlIrreflexiveObjectPropertyAxiom) {
		super(owlIrreflexiveObjectPropertyAxiom);
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
		return accept((ElkIrreflexiveObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkIrreflexiveObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
