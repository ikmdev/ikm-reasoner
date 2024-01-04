
package org.semanticweb.elk.reasoner.entailments.model;

/**
 * Instances of this interface represent entailment of inconsistency of
 * the ontology.
 * 
 * @author Peter Skocovsky
 */
public interface OntologyInconsistency extends Entailment {

	public static interface Visitor<O> {
		O visit(OntologyInconsistency inconsistentOntologyEntailment);
	}

}
