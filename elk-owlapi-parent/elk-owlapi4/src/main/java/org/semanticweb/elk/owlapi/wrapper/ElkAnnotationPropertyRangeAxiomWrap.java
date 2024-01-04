
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyRangeAxiom;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyRangeAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;

/**
 * Implements the {@link ElkAnnotationPropertyRangeAxiom} interface by wrapping
 * instances of {@link OWLAnnotationPropertyRangeAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkAnnotationPropertyRangeAxiomWrap<T extends OWLAnnotationPropertyRangeAxiom>
		extends ElkAnnotationAxiomWrap<T>
		implements ElkAnnotationPropertyRangeAxiom {

	public ElkAnnotationPropertyRangeAxiomWrap(
			T owlAnnotationPropertyRangeAxiom) {
		super(owlAnnotationPropertyRangeAxiom);
	}

	@Override
	public ElkAnnotationProperty getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public ElkIri getRange() {
		return converter.convert(getRange(owlObject));
	}

	@Override
	public <O> O accept(ElkAnnotationPropertyRangeAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkPropertyRangeAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyRangeAxiomVisitor<O>) visitor);
	}
}