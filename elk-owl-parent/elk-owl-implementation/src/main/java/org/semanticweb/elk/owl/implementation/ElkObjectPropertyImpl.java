 
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyExpressionVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Object_Properties">Object Property<a> in
 * the OWL 2 specification.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 */
public class ElkObjectPropertyImpl extends ElkIriObject implements ElkEntity,
		ElkObjectProperty {

	ElkObjectPropertyImpl(ElkIri iri) {
		super(iri);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.OBJECT_PROPERTY;
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkObjectPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyExpressionVisitor<O> visitor) {
		return accept((ElkObjectPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return accept((ElkObjectPropertyVisitor<O>) visitor);
	}

}
