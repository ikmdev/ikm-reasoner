
package org.semanticweb.elk.owlapi.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentDataPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkEquivalentDataPropertiesAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;

/**
 * Implements the {@link ElkEquivalentDataPropertiesAxiom} interface by wrapping
 * instances of {@link OWLEquivalentDataPropertiesAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkEquivalentDataPropertiesAxiomWrap<T extends OWLEquivalentDataPropertiesAxiom>
		extends ElkDataPropertyAxiomWrap<T> implements
		ElkEquivalentDataPropertiesAxiom {

	public ElkEquivalentDataPropertiesAxiomWrap(
			T owlEquivalentDataPropertiesAxiom) {
		super(owlEquivalentDataPropertiesAxiom);
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
		return accept((ElkEquivalentDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkEquivalentDataPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}