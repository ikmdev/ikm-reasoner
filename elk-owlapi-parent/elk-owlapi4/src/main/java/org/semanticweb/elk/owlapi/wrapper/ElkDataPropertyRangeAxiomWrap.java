
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyRangeAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;

/**
 * Implements the {@link ElkDataPropertyRangeAxiom} interface by wrapping
 * instances of {@link OWLDataPropertyRangeAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataPropertyRangeAxiomWrap<T extends OWLDataPropertyRangeAxiom>
		extends ElkDataPropertyAxiomWrap<T> implements
		ElkDataPropertyRangeAxiom {

	public ElkDataPropertyRangeAxiomWrap(T owlDataPropertyRangeAxiom) {
		super(owlDataPropertyRangeAxiom);
	}

	@Override
	public ElkDataPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkDataRange getRange() {
		return converter.convert(getRange(owlObject));
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRangeAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataPropertyRangeAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}