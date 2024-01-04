
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDisjointObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkDisjointObjectPropertiesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 * Implements the {@link ElkDisjointObjectPropertiesAxiom} interface by wrapping
 * instances of {@link OWLDisjointObjectPropertiesAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDisjointObjectPropertiesAxiomWrap<T extends OWLDisjointObjectPropertiesAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkDisjointObjectPropertiesAxiom {

	public ElkDisjointObjectPropertiesAxiomWrap(
			T owlDisjointObjectPropertiesAxiom) {
		super(owlDisjointObjectPropertiesAxiom);
	}

	@Override
	public List<? extends ElkObjectPropertyExpression> getObjectPropertyExpressions() {
		List<ElkObjectPropertyExpression> result = new ArrayList<ElkObjectPropertyExpression>();
		for (OWLObjectPropertyExpression ope : this.owlObject.getProperties()) {
			result.add(converter.convert(ope));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDisjointObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDisjointObjectPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
