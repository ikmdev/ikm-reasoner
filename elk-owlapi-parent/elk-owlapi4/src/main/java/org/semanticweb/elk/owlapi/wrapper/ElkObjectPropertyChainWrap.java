
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyChainVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 * Implements the {@link ElkObjectPropertyChain} interface by wrapping lists of
 * {@link OWLObjectPropertyExpression}s. The object corresponds to subchain
 * expression of the axiom.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectPropertyChainWrap<T extends List<? extends OWLObjectPropertyExpression>>
		extends ElkSubObjectPropertyExpressionWrap<T>
		implements ElkObjectPropertyChain {

	public ElkObjectPropertyChainWrap(T owlSubPropertyChainOfAxiom) {
		super(owlSubPropertyChainOfAxiom);
	}

	@Override
	public List<? extends ElkObjectPropertyExpression> getObjectPropertyExpressions() {
		List<ElkObjectPropertyExpression> result = new ArrayList<ElkObjectPropertyExpression>();
		for (OWLObjectPropertyExpression ope : this.owlObject) {
			result.add(converter.convert(ope));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectPropertyChainVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyChainVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
