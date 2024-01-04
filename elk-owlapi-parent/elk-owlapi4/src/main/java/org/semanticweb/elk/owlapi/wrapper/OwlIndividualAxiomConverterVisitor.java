
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;

/**
 * 
 * An implementation of the visitor pattern for OWL axioms to convert OWL
 * individual axioms axioms to the corresponding ELK assertion axioms.
 * Conversion of unsupported axioms throws an {@link IllegalArgumentException}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public final class OwlIndividualAxiomConverterVisitor extends
		AbstractOwlAxiomConverterVisitor<ElkAssertionAxiom> {

	private static OwlIndividualAxiomConverterVisitor INSTANCE_ = new OwlIndividualAxiomConverterVisitor();

	private OwlIndividualAxiomConverterVisitor() {
	}

	public static OwlIndividualAxiomConverterVisitor getInstance() {
		return INSTANCE_;
	}

	protected static OwlConverter CONVERTER = OwlConverter.getInstance();

	@Override
	protected Class<ElkAssertionAxiom> getTargetClass() {
		return ElkAssertionAxiom.class;
	}

	@Override
	public ElkAssertionAxiom visit(OWLNegativeDataPropertyAssertionAxiom axiom) {
		return CONVERTER.convert(axiom);
	}

	@Override
	public ElkAssertionAxiom visit(OWLDifferentIndividualsAxiom axiom) {
		return CONVERTER.convert(axiom);
	}

	@Override
	public ElkAssertionAxiom visit(OWLObjectPropertyAssertionAxiom axiom) {
		return CONVERTER.convert(axiom);
	}

	@Override
	public ElkAssertionAxiom visit(OWLClassAssertionAxiom axiom) {
		return CONVERTER.convert(axiom);
	}

	@Override
	public ElkAssertionAxiom visit(OWLDataPropertyAssertionAxiom axiom) {
		return CONVERTER.convert(axiom);
	}

	@Override
	public ElkAssertionAxiom visit(OWLSameIndividualAxiom axiom) {
		return CONVERTER.convert(axiom);
	}
}
