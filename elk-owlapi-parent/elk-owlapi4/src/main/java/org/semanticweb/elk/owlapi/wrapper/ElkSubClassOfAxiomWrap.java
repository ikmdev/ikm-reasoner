
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubClassOfAxiomVisitor;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

/**
 * Implements the {@link ElkSubClassOfAxiom} interface by wrapping instances of
 * {@link OWLSubClassOfAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkSubClassOfAxiomWrap<T extends OWLSubClassOfAxiom> extends
		ElkClassAxiomWrap<T> implements ElkSubClassOfAxiom {

	public ElkSubClassOfAxiomWrap(T owlSubClassOfAxiom) {
		super(owlSubClassOfAxiom);
	}

	@Override
	public ElkClassExpression getSubClassExpression() {
		return converter.convert(this.owlObject.getSubClass());
	}

	@Override
	public ElkClassExpression getSuperClassExpression() {
		return converter.convert(this.owlObject.getSuperClass());
	}

	@Override
	public <O> O accept(ElkClassAxiomVisitor<O> visitor) {
		return accept((ElkSubClassOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubClassOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
