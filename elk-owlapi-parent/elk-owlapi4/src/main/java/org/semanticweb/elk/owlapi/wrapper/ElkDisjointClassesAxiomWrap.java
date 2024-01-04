
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDisjointClassesAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;

/**
 * Implements the {@link ElkDisjointClassesAxiom} interface by wrapping
 * instances of {@link OWLDisjointClassesAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDisjointClassesAxiomWrap<T extends OWLDisjointClassesAxiom>
		extends ElkClassAxiomWrap<T> implements ElkDisjointClassesAxiom {

	public ElkDisjointClassesAxiomWrap(T owlDisjointClassesAxiom) {
		super(owlDisjointClassesAxiom);
	}

	@Override
	public List<? extends ElkClassExpression> getClassExpressions() {
		List<ElkClassExpression> result = new ArrayList<ElkClassExpression>();
		for (OWLClassExpression ce : this.owlObject.getClassExpressions()) {
			result.add(converter.convert(ce));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkClassAxiomVisitor<O> visitor) {
		return accept((ElkDisjointClassesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDisjointClassesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
