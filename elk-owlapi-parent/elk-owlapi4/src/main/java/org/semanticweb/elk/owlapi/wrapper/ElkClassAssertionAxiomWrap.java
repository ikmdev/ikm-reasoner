
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.visitors.ElkAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassAssertionAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;

/**
 * Implements the {@link ElkClassAssertionAxiom} interface by wrapping instances
 * of {@link OWLClassAssertionAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkClassAssertionAxiomWrap<T extends OWLClassAssertionAxiom>
		extends ElkAssertionAxiomWrap<T> implements ElkClassAssertionAxiom {

	public ElkClassAssertionAxiomWrap(T owlClassAssertionAxiom) {
		super(owlClassAssertionAxiom);
	}

	@Override
	public ElkIndividual getIndividual() {
		return converter.convert(this.owlObject.getIndividual());
	}

	@Override
	public ElkClassExpression getClassExpression() {
		return converter.convert(this.owlObject.getClassExpression());
	}

	@Override
	public <O> O accept(ElkAssertionAxiomVisitor<O> visitor) {
		return accept((ElkClassAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkClassAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
