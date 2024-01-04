
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLEntity;

/**
 * Implements the {@link ElkEntity} interface by wrapping instances of
 * {@link OWLEntity}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public abstract class ElkEntityWrap<T extends OWLEntity> extends
		ElkObjectWrap<T> implements ElkEntity {

	ElkEntityWrap(T owlEntity) {
		super(owlEntity);
	}

	@Override
	public ElkIri getIri() {
		return converter.convert(this.owlObject.getIRI());
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkEntityVisitor<O>) visitor);
	}

}
