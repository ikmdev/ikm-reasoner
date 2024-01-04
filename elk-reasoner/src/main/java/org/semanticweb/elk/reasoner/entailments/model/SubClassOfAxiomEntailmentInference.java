
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * How was {@link ElkSubClassOfAxiom} entailed.
 * 
 * @author Peter Skocovsky
 */
public interface SubClassOfAxiomEntailmentInference
		extends AxiomEntailmentInference<ElkSubClassOfAxiom> {

	@Override
	SubClassOfAxiomEntailment getConclusion();

	public static interface Visitor<O>
			extends DerivedClassInclusionEntailsSubClassOfAxiom.Visitor<O>,
			SubClassInconsistencyEntailsSubClassOfAxiom.Visitor<O> {
		// combined visitor
	}

}
