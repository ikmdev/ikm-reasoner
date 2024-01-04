
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectIntersectionOfVisitor;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;

/**
 * Implements the {@link ElkObjectIntersectionOf} interface by wrapping
 * instances of {@link OWLObjectIntersectionOf}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectIntersectionOfWrap<T extends OWLObjectIntersectionOf>
		extends ElkClassExpressionWrap<T> implements ElkObjectIntersectionOf {

	ElkObjectIntersectionOfWrap(T owlObjectIntersectionOf) {
		super(owlObjectIntersectionOf);
	}

	@Override
	public List<? extends ElkClassExpression> getClassExpressions() {
		List<ElkClassExpression> result = new ArrayList<ElkClassExpression>();
		for (OWLClassExpression ce : this.owlObject.getOperands()) {
			result.add(converter.convert(ce));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectIntersectionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectIntersectionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
