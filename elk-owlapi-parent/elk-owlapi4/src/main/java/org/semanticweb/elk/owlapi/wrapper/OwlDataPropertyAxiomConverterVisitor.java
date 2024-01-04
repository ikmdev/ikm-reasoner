
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;

/**
 * An implementation of the visitor pattern for OWL axioms to convert OWL data
 * property axioms to the corresponding ELK data property axioms.
 * 
 * @author "Yevgeny Kazakov"
 */
public class OwlDataPropertyAxiomConverterVisitor
		extends AbstractOwlAxiomConverterVisitor<ElkDataPropertyAxiom> {

	private static OwlDataPropertyAxiomConverterVisitor INSTANCE_ = new OwlDataPropertyAxiomConverterVisitor();

	private OwlDataPropertyAxiomConverterVisitor() {
	}

	public static OwlDataPropertyAxiomConverterVisitor getInstance() {
		return INSTANCE_;
	}

	protected static OwlConverter CONVERTER = OwlConverter.getInstance();

	@Override
	protected Class<ElkDataPropertyAxiom> getTargetClass() {
		return ElkDataPropertyAxiom.class;
	}

	@Override
	public ElkDataPropertyAxiom visit(
			OWLDataPropertyDomainAxiom owlDataPropertyDomainAxiom) {
		return CONVERTER.convert(owlDataPropertyDomainAxiom);
	}

	@Override
	public ElkDataPropertyAxiom visit(
			OWLDataPropertyRangeAxiom owlDataPropertyRangeAxiom) {
		return CONVERTER.convert(owlDataPropertyRangeAxiom);
	}

	@Override
	public ElkDataPropertyAxiom visit(
			OWLDisjointDataPropertiesAxiom owlDisjointDataPropertiesAxiom) {
		return CONVERTER.convert(owlDisjointDataPropertiesAxiom);
	}

	@Override
	public ElkDataPropertyAxiom visit(
			OWLEquivalentDataPropertiesAxiom owlEquivalentDataProperties) {
		return CONVERTER.convert(owlEquivalentDataProperties);
	}

	@Override
	public ElkDataPropertyAxiom visit(
			OWLFunctionalDataPropertyAxiom owlFunctionalDataPropertyAxiom) {
		return CONVERTER.convert(owlFunctionalDataPropertyAxiom);
	}

	@Override
	public ElkDataPropertyAxiom visit(
			OWLSubDataPropertyOfAxiom owlSubDataPropertyOfAxiom) {
		return CONVERTER.convert(owlSubDataPropertyOfAxiom);
	}

}
