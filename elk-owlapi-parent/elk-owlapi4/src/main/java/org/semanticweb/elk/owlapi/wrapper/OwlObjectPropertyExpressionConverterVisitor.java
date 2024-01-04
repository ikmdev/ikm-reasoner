 
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectInverseOf;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLPropertyExpressionVisitorEx;

/**
 * A visitor class for converting instances of
 * {@link OWLObjectPropertyExpression} to the corresponding instances of
 * {@link ElkObjectPropertyExpression}.
 * 
 * @author "Yevgeny Kazakov"
 * @author Frantisek Simancik
 * @author Peter Skocovsky
 * 
 */
public class OwlObjectPropertyExpressionConverterVisitor implements
		OWLPropertyExpressionVisitorEx<ElkObjectPropertyExpression> {

	private static OwlObjectPropertyExpressionConverterVisitor INSTANCE_ = new OwlObjectPropertyExpressionConverterVisitor();

	private static OwlObjectInverseOfConverterVisitor OWL_OBJECT_INVERSE_OF_CONVERTER_ = OwlObjectInverseOfConverterVisitor
			.getInstance();

	private OwlObjectPropertyExpressionConverterVisitor() {
	}

	public static OwlObjectPropertyExpressionConverterVisitor getInstance() {
		return INSTANCE_;
	}

	@Override
	public ElkObjectPropertyExpression visit(OWLObjectProperty property) {
		return new ElkObjectPropertyWrap<OWLObjectProperty>(property);
	}

	@Override
	public ElkObjectPropertyExpression visit(OWLObjectInverseOf property) {
		return property.getInverse().accept(OWL_OBJECT_INVERSE_OF_CONVERTER_);
	}

	@Override
	public ElkObjectPropertyExpression visit(OWLDataProperty property) {
		throw new IllegalArgumentException(
				OWLDataProperty.class.getSimpleName()
						+ " cannot be converted to "
						+ ElkObjectPropertyExpression.class.getSimpleName());
	}

	@Override
	public ElkObjectPropertyExpression visit(OWLAnnotationProperty property) {
		throw new IllegalArgumentException(
				OWLDataProperty.class.getSimpleName()
						+ " cannot be converted to "
						+ ElkObjectPropertyExpression.class.getSimpleName());
	}
	
}
