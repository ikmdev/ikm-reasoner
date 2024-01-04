
package org.semanticweb.elk.reasoner.entailments.impl;

import org.semanticweb.elk.reasoner.entailments.model.Entailment;
import org.semanticweb.elk.reasoner.entailments.model.OntologyInconsistency;

public class OntologyInconsistencyImpl extends AbstractEntailment
		implements OntologyInconsistency {

	public static final OntologyInconsistencyImpl INSTANCE = new OntologyInconsistencyImpl();

	@Override
	public <O> O accept(final Entailment.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
