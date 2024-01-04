
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDisjointUnionAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;

/**
 * Implements the {@link ElkDisjointUnionAxiom} interface by wrapping instances
 * of {@link OWLDisjointUnionAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDisjointUnionAxiomWrap<T extends OWLDisjointUnionAxiom> extends
		ElkClassAxiomWrap<T> implements ElkDisjointUnionAxiom {

	public ElkDisjointUnionAxiomWrap(T owlClassAxiom) {
		super(owlClassAxiom);
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
	public ElkClass getDefinedClass() {
		return new ElkClassWrap<OWLClass>(this.owlObject.getOWLClass());
	}

	@Override
	public <O> O accept(ElkClassAxiomVisitor<O> visitor) {
		return accept((ElkDisjointUnionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDisjointUnionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
