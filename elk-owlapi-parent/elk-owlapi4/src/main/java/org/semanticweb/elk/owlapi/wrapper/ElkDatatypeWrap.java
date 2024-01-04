
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.predefined.ElkEntityType;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkDatatypeVisitor;
import org.semanticweb.elk.owl.visitors.ElkEntityVisitor;
import org.semanticweb.owlapi.model.OWLDatatype;

/**
 * Implements the {@link ElkDatatype} interface by wrapping instances of
 * {@link OWLDatatype}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDatatypeWrap<T extends OWLDatatype> extends ElkEntityWrap<T>
		implements ElkDatatype {

	public ElkDatatypeWrap(T owlDatatype) {
		super(owlDatatype);
	}

	@Override
	public ElkEntityType getEntityType() {
		return ElkEntityType.DATATYPE;
	}

	@Override
	public <O> O accept(ElkEntityVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDatatypeVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDatatypeVisitor<O> visitor) {
		return visitor.visit(this);
	}

}