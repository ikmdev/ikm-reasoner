
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;

/**
 * {@link OntologyInconsistency} was entailed because inconsistency of some
 * {@link ElkIndividual} was derived.
 * <p>
 * {@link #getIndividual()} returns the inconsistent {@link ElkIndividual} and
 * {@link #getReason()} returns a {@link ClassInconsistency} with
 * {@link ClassInconsistency#getDestination()} corresponding to the inconsistent
 * {@link ElkIndividual}.
 * 
 * @author Peter Skocovsky
 */
public interface IndividualInconsistencyEntailsOntologyInconsistency
		extends OntologyInconsistencyEntailmentInference,
		HasReason<ClassInconsistency> {

	ElkIndividual getIndividual();

	public static interface Visitor<O> {
		O visit(IndividualInconsistencyEntailsOntologyInconsistency individualInconsistencyEntailsOntologyInconsistency);
	}

}
