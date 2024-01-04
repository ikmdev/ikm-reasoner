
package org.semanticweb.elk.owlapi.wrapper;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyOfAxiomVisitor;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLSubPropertyChainOfAxiom;

/**
 * Implements the {@link ElkSubObjectPropertyOfAxiom} interface by wrapping
 * instances of {@link OWLSubPropertyChainOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkSubObjectPropertyChainOfAxiomWrap<T extends OWLSubPropertyChainOfAxiom>
		extends ElkObjectPropertyAxiomWrap<T>
		implements ElkSubObjectPropertyOfAxiom {

	public ElkSubObjectPropertyChainOfAxiomWrap(T owlSubPropertyChainOfAxiom) {
		super(owlSubPropertyChainOfAxiom);
	}

	@Override
	public ElkSubObjectPropertyExpression getSubObjectPropertyExpression() {
		return new ElkObjectPropertyChainWrap<List<? extends OWLObjectPropertyExpression>>(
				this.owlObject.getPropertyChain());
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
