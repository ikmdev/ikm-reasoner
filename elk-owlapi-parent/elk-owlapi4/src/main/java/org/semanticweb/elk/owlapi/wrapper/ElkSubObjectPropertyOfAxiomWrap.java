
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyOfAxiomVisitor;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;

/**
 * Implements the {@link ElkSubObjectPropertyOfAxiom} interface by wrapping
 * instances of {@link OWLSubObjectPropertyOfAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkSubObjectPropertyOfAxiomWrap<T extends OWLSubObjectPropertyOfAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkSubObjectPropertyOfAxiom {

	public ElkSubObjectPropertyOfAxiomWrap(T owlSubObjectPropertyOfAxiom) {
		super(owlSubObjectPropertyOfAxiom);
	}

	@Override
	public ElkSubObjectPropertyExpression getSubObjectPropertyExpression() {
		return converter.convert(this.owlObject.getSubProperty());
	}

	@Override
	public ElkObjectPropertyExpression getSuperObjectPropertyExpression() {
		return converter.convert(this.owlObject.getSuperProperty());
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSubObjectPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
