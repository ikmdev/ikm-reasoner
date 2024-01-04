
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkInverseObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkInverseObjectPropertiesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;

/**
 * Implements the {@link ElkInverseObjectPropertiesAxiom} interface by wrapping
 * instances of {@link OWLInverseObjectPropertiesAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkInverseObjectPropertiesAxiomWrap<T extends OWLInverseObjectPropertiesAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkInverseObjectPropertiesAxiom {

	public ElkInverseObjectPropertiesAxiomWrap(T owlInverseObjectPropertiesAxiom) {
		super(owlInverseObjectPropertiesAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getFirstObjectPropertyExpression() {
		return converter.convert(this.owlObject.getFirstProperty());
	}

	@Override
	public ElkObjectPropertyExpression getSecondObjectPropertyExpression() {
		return converter.convert(this.owlObject.getSecondProperty());
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkInverseObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkInverseObjectPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
