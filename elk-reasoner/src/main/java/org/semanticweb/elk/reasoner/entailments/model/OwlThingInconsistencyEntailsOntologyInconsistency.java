
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;

/**
 * {@link OntologyInconsistency} is entailed because inconsistency of
 * {@code owl:Thing} was derived.
 * <p>
 * {@link #getReason()} returns a {@link ClassInconsistency} with
 * {@link ClassInconsistency#getDestination()} = {@code owl:Thing}.
 * 
 * @author Peter Skocovsky
 */
public interface OwlThingInconsistencyEntailsOntologyInconsistency
		extends OntologyInconsistencyEntailmentInference,
		HasReason<ClassInconsistency> {

	public static interface Visitor<O> {
		O visit(OwlThingInconsistencyEntailsOntologyInconsistency owlThingInconsistencyEntailsOntologyInconsistency);
	}

}
