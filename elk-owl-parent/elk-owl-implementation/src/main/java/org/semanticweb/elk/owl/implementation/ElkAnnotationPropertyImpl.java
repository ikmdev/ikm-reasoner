
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyVisitor;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

public class ElkAnnotationPropertyImpl extends ElkIriObject implements
		ElkAnnotationProperty {

	ElkAnnotationPropertyImpl(ElkIri iri) {
		super(iri);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.ANNOTATION_PROPERTY;
	}

	@Override
	public <O> O accept(ElkAnnotationPropertyVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyVisitor<O>) visitor);
	}

}
