 
package org.semanticweb.elk.reasoner.entailments.model;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * An axiom was entailed because {@link OntologyInconsistency} was entailed.
 * <p>
 * The entailed axiom can be retrieved from {@link #getConclusion()} and the
 * reason, i.e. {@link OntologyInconsistency}, should be the only member of
 * {@link #getPremises()}.
 * 
 * @author Peter Skocovsky
 */
public interface OntologyInconsistencyEntailsAnyAxiom
		extends AxiomEntailmentInference<ElkAxiom> {

	@Override
	List<? extends OntologyInconsistency> getPremises();

	public static interface Visitor<O> {
		O visit(OntologyInconsistencyEntailsAnyAxiom ontologyInconsistencyEntailsAnyAxiom);
	}

}
