 
package org.semanticweb.elk.reasoner.entailments.model;

/**
 * Instances of this interface represent something that may be entailed from
 * other entailments or conclusions derived by the reasoner, e.g., axiom or
 * inconsistency of the ontology.
 * 
 * @author Peter Skocovsky
 */
public interface Entailment {

	<O> O accept(Visitor<O> visitor);

	public static interface Visitor<O> extends AxiomEntailment.Visitor<O>,
			OntologyInconsistency.Visitor<O> {
		// combined interface
	}

}
