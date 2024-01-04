 
package org.semanticweb.elk.reasoner.entailments.model;

/**
 * How was {@link OntologyInconsistency} entailed.
 * 
 * @author Peter Skocovsky
 */
public interface OntologyInconsistencyEntailmentInference
		extends EntailmentInference {

	@Override
	OntologyInconsistency getConclusion();

	public static interface Visitor<O> extends
			IndividualInconsistencyEntailsOntologyInconsistency.Visitor<O>,
			OwlThingInconsistencyEntailsOntologyInconsistency.Visitor<O>,
			TopObjectPropertyInBottomEntailsOntologyInconsistency.Visitor<O> {
		// combined visitor
	}

}
