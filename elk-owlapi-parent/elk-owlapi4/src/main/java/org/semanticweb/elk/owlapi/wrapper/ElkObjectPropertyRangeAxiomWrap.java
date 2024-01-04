
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyRangeAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;

/**
 * Implements the {@link ElkObjectPropertyRangeAxiom} interface by wrapping
 * instances of {@link OWLObjectPropertyRangeAxiom}.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectPropertyRangeAxiomWrap<T extends OWLObjectPropertyRangeAxiom>
		extends ElkObjectPropertyAxiomWrap<T>
		implements ElkObjectPropertyRangeAxiom {

	public ElkObjectPropertyRangeAxiomWrap(T owlObjectPropertyRangeAxiom) {
		super(owlObjectPropertyRangeAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkClassExpression getRange() {
		return converter.convert(getRange(owlObject));
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkPropertyRangeAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyRangeAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
