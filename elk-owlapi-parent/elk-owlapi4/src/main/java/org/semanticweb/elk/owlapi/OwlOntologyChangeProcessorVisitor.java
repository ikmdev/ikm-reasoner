
package org.semanticweb.elk.owlapi;


import org.semanticweb.elk.loading.ElkLoadingException;
import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomProcessor;
import org.semanticweb.elk.owlapi.wrapper.OwlConverter;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyChangeVisitor;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.RemoveOntologyAnnotation;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class OwlOntologyChangeProcessorVisitor implements
		OWLOntologyChangeVisitor {

	// logger for this class
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(OwlOntologyChangeProcessorVisitor.class);

	private static final OwlConverter OWL_CONVERTER_ = OwlConverter
			.getInstance();

	private final ElkAxiomProcessor axiomInserter_, axiomDeleter_;
	
	private ElkLoadingException error_ = null;

	OwlOntologyChangeProcessorVisitor(ElkAxiomProcessor axiomInserter,
			ElkAxiomProcessor axiomDeleter) {
		this.axiomInserter_ = axiomInserter;
		this.axiomDeleter_ = axiomDeleter;
	}

	protected void defaultVisit(OWLOntologyChange change) {
		error_ = new ElkLoadingException(
				"Ontology change " + change.toString() + " is not supported");
	}
	
	public ElkLoadingException getError() {
		return error_;
	}

	@Override
	public void visit(RemoveAxiom arg) {
		ElkAxiom elkAxiom = OWL_CONVERTER_.convert(arg.getAxiom());

		axiomDeleter_.visit(elkAxiom);

		if (LOGGER_.isTraceEnabled())
			LOGGER_.trace("removing " + arg.getAxiom());

	}

	@Override
	public void visit(AddAxiom arg) {
		ElkAxiom elkAxiom = OWL_CONVERTER_.convert(arg.getAxiom());

		axiomInserter_.visit(elkAxiom);

		if (LOGGER_.isTraceEnabled())
			LOGGER_.trace("adding " + arg.getAxiom());

	}

	@Override
	public void visit(SetOntologyID change) {
		defaultVisit(change);
	}

	@Override
	public void visit(AddImport change) {
		defaultVisit(change);
	}

	@Override
	public void visit(RemoveImport change) {
		defaultVisit(change);
	}

	@Override
	public void visit(AddOntologyAnnotation change) {
		defaultVisit(change);
	}

	@Override
	public void visit(RemoveOntologyAnnotation change) {
		defaultVisit(change);
	}

}
