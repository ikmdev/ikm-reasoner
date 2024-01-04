
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectUnionOf;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectUnionOfVisitor;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;

/**
 * Implements the {@link ElkObjectUnionOf} interface by wrapping instances of
 * {@link OWLObjectUnionOf}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectUnionOfWrap<T extends OWLObjectUnionOf> extends
		ElkClassExpressionWrap<T> implements ElkObjectUnionOf {

	ElkObjectUnionOfWrap(T owlObjectUnionOf) {
		super(owlObjectUnionOf);
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
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkObjectUnionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
