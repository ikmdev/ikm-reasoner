
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationSubject;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationValue;
import org.semanticweb.elk.owl.interfaces.ElkAnonymousIndividual;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationSubjectVisitorEx;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLAnnotationValueVisitorEx;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;

/**
 * Converts OWL annotation subjects and values (IRIs, anonymous individuals, and literals)
 * 
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 *
 */
public class OwlAnnotationSubjectValueVisitor implements OWLAnnotationSubjectVisitorEx<ElkAnnotationSubject>, OWLAnnotationValueVisitorEx<ElkAnnotationValue> {

	private final static OwlAnnotationSubjectValueVisitor INSTANCE_ = new OwlAnnotationSubjectValueVisitor();
	protected static OwlConverter CONVERTER = OwlConverter.getInstance();
	
	static OwlAnnotationSubjectValueVisitor getInstance() {
		return INSTANCE_;
	}
	
	public ElkAnnotationSubject visit(OWLAnnotationSubject subject) {
		return subject.accept(this);
	}
	
	public ElkAnnotationValue visit(OWLAnnotationValue value) {
		return value.accept(this);
	}
	
	@Override
	public ElkIri visit(IRI iri) {
		return CONVERTER.convert(iri);
	}

	@Override
	public ElkAnonymousIndividual visit(OWLAnonymousIndividual anon) {
		return CONVERTER.convert(anon);
	}

	@Override
	public ElkLiteral visit(OWLLiteral literal) {
		return CONVERTER.convert(literal);
	}
}
