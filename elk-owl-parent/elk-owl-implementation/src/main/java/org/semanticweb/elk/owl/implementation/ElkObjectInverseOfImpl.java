
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectInverseOf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.visitors.ElkObjectInverseOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Inverse_Object_Properties">Inverse Object
 * Property<a> in the OWL 2 specification.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 */
public class ElkObjectInverseOfImpl extends ElkObjectImpl implements
		ElkObjectInverseOf {

	protected final ElkObjectProperty objectProperty_;

	ElkObjectInverseOfImpl(ElkObjectProperty property) {
		this.objectProperty_ = property;
	}

	@Override
	public ElkObjectProperty getObjectProperty() {
		return objectProperty_;
	}

	@Override
	public <O> O accept(ElkObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectInverseOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectInverseOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectInverseOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectInverseOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
