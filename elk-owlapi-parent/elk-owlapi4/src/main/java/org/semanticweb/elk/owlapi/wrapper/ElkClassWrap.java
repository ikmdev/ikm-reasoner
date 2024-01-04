
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassVisitor;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.owlapi.model.OWLClass;

/**
 * Implements the {@link ElkClass} interface by wrapping instances of
 * {@link OWLClass}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkClassWrap<T extends OWLClass> extends ElkEntityWrap<T>
		implements ElkClass {

	public ElkClassWrap(T owlClass) {
		super(owlClass);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.CLASS;
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkClassVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkClassVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
