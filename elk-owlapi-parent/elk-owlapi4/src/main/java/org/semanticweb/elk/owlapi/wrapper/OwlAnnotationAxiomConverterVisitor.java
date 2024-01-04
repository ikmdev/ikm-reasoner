
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;

/**
 * 
 * An implementation of the visitor pattern for OWL axioms to convert OWL class
 * axioms to the corresponding ELK class axioms. Conversion of unsupported
 * axioms throws an {@link IllegalArgumentException}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public final class OwlAnnotationAxiomConverterVisitor extends
		AbstractOwlAxiomConverterVisitor<ElkAnnotationAxiom> {

	private static OwlAnnotationAxiomConverterVisitor INSTANCE_ = new OwlAnnotationAxiomConverterVisitor();

	private OwlAnnotationAxiomConverterVisitor() {
	}

	public static OwlAnnotationAxiomConverterVisitor getInstance() {
		return INSTANCE_;
	}

	protected static OwlConverter CONVERTER = OwlConverter.getInstance();

	@Override
	protected Class<ElkAnnotationAxiom> getTargetClass() {
		return ElkAnnotationAxiom.class;
	}

	@Override
	public ElkAnnotationAxiom visit(OWLAnnotationAssertionAxiom owlAnnotationAssertion) {
		return new ElkAnnotationAssertionAxiomWrap<OWLAnnotationAssertionAxiom>(owlAnnotationAssertion);
	}

	@Override
	public ElkAnnotationAxiom visit(OWLAnnotationPropertyDomainAxiom owlAnnotationPropertyDomain) {
		return new ElkAnnotationPropertyDomainAxiomWrap<OWLAnnotationPropertyDomainAxiom>(owlAnnotationPropertyDomain);
	}

	@Override
	public ElkAnnotationAxiom visit(OWLAnnotationPropertyRangeAxiom owlAnnotationPropertyRange) {
		return new ElkAnnotationPropertyRangeAxiomWrap<OWLAnnotationPropertyRangeAxiom>(owlAnnotationPropertyRange);
	}

	@Override
	public ElkAnnotationAxiom visit(OWLSubAnnotationPropertyOfAxiom owlSubAnnotationPropertyOfAxiom) {
		return new ElkSubAnnotationPropertyOfAxiomWrap<OWLSubAnnotationPropertyOfAxiom>(owlSubAnnotationPropertyOfAxiom);
	}

}
