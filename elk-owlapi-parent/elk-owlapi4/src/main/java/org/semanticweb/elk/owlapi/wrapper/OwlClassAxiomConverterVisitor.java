
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

/**
 * 
 * An implementation of the visitor pattern for OWL axioms to convert OWL class
 * axioms to the corresponding ELK class axioms.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public final class OwlClassAxiomConverterVisitor extends
		AbstractOwlAxiomConverterVisitor<ElkClassAxiom> {

	private static OwlClassAxiomConverterVisitor INSTANCE_ = new OwlClassAxiomConverterVisitor();

	private OwlClassAxiomConverterVisitor() {
	}

	public static OwlClassAxiomConverterVisitor getInstance() {
		return INSTANCE_;
	}

	protected static OwlConverter CONVERTER = OwlConverter.getInstance();

	@Override
	protected Class<ElkClassAxiom> getTargetClass() {
		return ElkClassAxiom.class;
	}

	@Override
	public ElkClassAxiom visit(OWLDisjointClassesAxiom owlDisjointClasses) {
		return CONVERTER.convert(owlDisjointClasses);
	}

	@Override
	public ElkClassAxiom visit(OWLDisjointUnionAxiom owlDisjointUnionAxiom) {
		return CONVERTER.convert(owlDisjointUnionAxiom);
	}

	@Override
	public ElkClassAxiom visit(
			OWLEquivalentClassesAxiom owlEquivalentClassesAxiom) {
		return CONVERTER.convert(owlEquivalentClassesAxiom);
	}

	@Override
	public ElkClassAxiom visit(OWLSubClassOfAxiom owlSubClassOfAxiom) {
		return CONVERTER.convert(owlSubClassOfAxiom);
	}

}
