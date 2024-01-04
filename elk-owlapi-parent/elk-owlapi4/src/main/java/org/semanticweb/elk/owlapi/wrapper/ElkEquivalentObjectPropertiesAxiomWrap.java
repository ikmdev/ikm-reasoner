
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkEquivalentObjectPropertiesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 * Implements the {@link ElkEquivalentObjectPropertiesAxiom} interface by
 * wrapping instances of {@link OWLEquivalentObjectPropertiesAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkEquivalentObjectPropertiesAxiomWrap<T extends OWLEquivalentObjectPropertiesAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkEquivalentObjectPropertiesAxiom {

	public ElkEquivalentObjectPropertiesAxiomWrap(
			T owlEquivalentObjectPropertiesAxiom) {
		super(owlEquivalentObjectPropertiesAxiom);
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
		return accept((ElkEquivalentObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkEquivalentObjectPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
