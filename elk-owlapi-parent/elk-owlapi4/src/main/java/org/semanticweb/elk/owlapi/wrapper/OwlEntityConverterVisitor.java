
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLEntityVisitorEx;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * An implementation of the visitor pattern for OWL entities to convert OWL
 * entities ranges to ELK entities.
 * 
 * @author "Yevgeny Kazakov"
 */
public class OwlEntityConverterVisitor
		implements OWLEntityVisitorEx<ElkEntity> {

	protected static OwlConverter CONVERTER = OwlConverter.getInstance();

	private static OwlEntityConverterVisitor INSTANCE_ = new OwlEntityConverterVisitor();

	public static OwlEntityConverterVisitor getInstance() {
		return INSTANCE_;
	}

	private OwlEntityConverterVisitor() {
	}

	@Override
	public ElkAnnotationProperty visit(
			OWLAnnotationProperty owlAnnotationproperty) {
		return CONVERTER.convert(owlAnnotationproperty);
	}

	@Override
	public ElkClass visit(OWLClass owlClass) {
		return CONVERTER.convert(owlClass);
	}

	@Override
	public ElkDataProperty visit(OWLDataProperty owlDataProperty) {
		return CONVERTER.convert(owlDataProperty);
	}

	@Override
	public ElkDatatype visit(OWLDatatype owlDatatype) {
		return CONVERTER.convert(owlDatatype);
	}

	@Override
	public ElkObjectProperty visit(OWLObjectProperty owlObjectProperty) {
		return CONVERTER.convert(owlObjectProperty);
	}

	@Override
	public ElkNamedIndividual visit(OWLNamedIndividual owlNamedIndividual) {
		return CONVERTER.convert(owlNamedIndividual);
	}

}
