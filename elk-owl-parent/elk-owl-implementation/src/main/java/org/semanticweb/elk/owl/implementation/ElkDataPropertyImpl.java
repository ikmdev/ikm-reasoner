
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyVisitor;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkDataProperty.
 * 
 * @author Markus Kroetzsch
 */
public class ElkDataPropertyImpl extends ElkIriObject implements ElkEntity,
		ElkDataProperty {

	ElkDataPropertyImpl(ElkIri iri) {
		super(iri);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.DATA_PROPERTY;
	}

	@Override
	public <O> O accept(ElkDataPropertyExpressionVisitor<O> visitor) {
		return accept((ElkDataPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return accept((ElkDataPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataPropertyVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
