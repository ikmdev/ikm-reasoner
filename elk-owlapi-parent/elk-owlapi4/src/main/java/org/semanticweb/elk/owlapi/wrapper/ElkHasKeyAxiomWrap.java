
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkHasKeyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkHasKeyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped {@link OWLHasKeyAxiom}
 */
public class ElkHasKeyAxiomWrap<T extends OWLHasKeyAxiom> extends
		ElkAxiomWrap<T> implements ElkHasKeyAxiom {

	public ElkHasKeyAxiomWrap(T owlAxiom) {
		super(owlAxiom);
	}

	@Override
	public ElkClassExpression getClassExpression() {
		return converter.convert(this.owlObject.getClassExpression());
	}

	@Override
	public List<? extends ElkObjectPropertyExpression> getObjectPropertyExpressions() {
		List<ElkObjectPropertyExpression> opes = new ArrayList<ElkObjectPropertyExpression>(
				this.owlObject.getObjectPropertyExpressions().size());
		for (OWLObjectPropertyExpression ope : this.owlObject
				.getObjectPropertyExpressions()) {
			opes.add(converter.convert(ope));
		}

		return opes;
	}

	@Override
	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions() {
		List<ElkDataPropertyExpression> dpes = new ArrayList<ElkDataPropertyExpression>(
				this.owlObject.getDataPropertyExpressions().size());
		for (OWLDataPropertyExpression dpe : this.owlObject
				.getDataPropertyExpressions()) {
			dpes.add(converter.convert(dpe));
		}

		return dpes;
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkHasKeyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkHasKeyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkHasKeyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}