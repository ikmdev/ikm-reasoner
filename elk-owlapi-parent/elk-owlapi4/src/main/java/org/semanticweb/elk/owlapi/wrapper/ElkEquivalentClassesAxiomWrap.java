 
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkEquivalentClassesAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;

/**
 * Implements the {@link ElkEquivalentClassesAxiom} interface by wrapping
 * instances of {@link OWLEquivalentClassesAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkEquivalentClassesAxiomWrap<T extends OWLEquivalentClassesAxiom>
		extends ElkClassAxiomWrap<T> implements ElkEquivalentClassesAxiom {

	public ElkEquivalentClassesAxiomWrap(T owlEquivalentClasses) {
		super(owlEquivalentClasses);
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
		return accept((ElkEquivalentClassesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkEquivalentClassesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
