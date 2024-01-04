
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubDataPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubDataPropertyOfAxiomVisitor;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;

/**
 * Implements the {@link ElkSubDataPropertyOfAxiom} interface by wrapping
 * instances of {@link OWLSubDataPropertyOfAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkSubDataPropertyOfAxiomWrap<T extends OWLSubDataPropertyOfAxiom>
		extends ElkDataPropertyAxiomWrap<T> implements
		ElkSubDataPropertyOfAxiom {

	public ElkSubDataPropertyOfAxiomWrap(T owlSubDataPropertyOfAxiom) {
		super(owlSubDataPropertyOfAxiom);
	}

	@Override
	public ElkDataPropertyExpression getSubDataPropertyExpression() {
		return converter.convert(this.owlObject.getSubProperty());
	}

	@Override
	public ElkDataPropertyExpression getSuperDataPropertyExpression() {
		return converter.convert(this.owlObject.getSuperProperty());
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSubDataPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubDataPropertyOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}