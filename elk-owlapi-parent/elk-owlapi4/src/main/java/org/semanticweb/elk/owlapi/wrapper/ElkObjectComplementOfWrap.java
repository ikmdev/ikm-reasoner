
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectComplementOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectComplementOfVisitor;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;

/**
 * Implements the {@link ElkObjectComplementOf} interface by wrapping instances
 * of {@link OWLObjectComplementOf}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectComplementOfWrap<T extends OWLObjectComplementOf> extends
		ElkClassExpressionWrap<T> implements ElkObjectComplementOf {

	ElkObjectComplementOfWrap(T owlObjectComplementOf) {
		super(owlObjectComplementOf);
	}

	@Override
	public ElkClassExpression getClassExpression() {
		return converter.convert(this.owlObject.getOperand());
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectComplementOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectComplementOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
