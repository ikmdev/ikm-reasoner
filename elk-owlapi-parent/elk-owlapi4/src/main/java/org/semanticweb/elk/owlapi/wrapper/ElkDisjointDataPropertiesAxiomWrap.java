
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointDataPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDisjointDataPropertiesAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;

/**
 * Implements the {@link ElkDisjointDataPropertiesAxiom} interface by wrapping
 * instances of {@link OWLDisjointDataPropertiesAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDisjointDataPropertiesAxiomWrap<T extends OWLDisjointDataPropertiesAxiom>
		extends ElkDataPropertyAxiomWrap<T> implements
		ElkDisjointDataPropertiesAxiom {

	public ElkDisjointDataPropertiesAxiomWrap(T owlDisjointDataPropertiesAxiom) {
		super(owlDisjointDataPropertiesAxiom);
	}

	@Override
	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions() {
		List<ElkDataPropertyExpression> result = new ArrayList<ElkDataPropertyExpression>();
		for (OWLDataPropertyExpression dpe : this.owlObject.getProperties()) {
			result.add(converter.convert(dpe));
		}
		return result;
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDisjointDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDisjointDataPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}