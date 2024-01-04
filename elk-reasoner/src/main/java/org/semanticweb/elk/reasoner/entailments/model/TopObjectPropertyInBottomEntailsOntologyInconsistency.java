
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

/**
 * {@link OntologyInconsistency} is entailed because inclusion of
 * {@code owl:topObjectProperty} in {@code owl:bottomObjectProperty} was
 * derived.
 * <p>
 * {@link #getReason()} returns a {@link SubPropertyChain} with
 * {@link SubPropertyChain#getSubChain()} = {@code owl:topObjectProperty} and
 * {@link SubPropertyChain#getSuperChain()} = {@code owl:bottomObjectProperty}.
 * 
 * @author Peter Skocovsky
 */
public interface TopObjectPropertyInBottomEntailsOntologyInconsistency extends
		OntologyInconsistencyEntailmentInference, HasReason<SubPropertyChain> {

	public static interface Visitor<O> {
		O visit(TopObjectPropertyInBottomEntailsOntologyInconsistency topObjectPropertyInBottomEntailsOntologyInconsistency);
	}

}
