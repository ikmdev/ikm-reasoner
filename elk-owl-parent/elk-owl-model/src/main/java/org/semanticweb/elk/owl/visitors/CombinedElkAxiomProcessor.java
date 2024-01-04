
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Processes axioms with the provided processor and visits them before and after
 * the processing by the provided visitors.
 * 
 * @author Peter Skocovsky
 */
public class CombinedElkAxiomProcessor implements ElkAxiomProcessor {

	private final ElkAxiomProcessor[] processors_;

	public CombinedElkAxiomProcessor(ElkAxiomProcessor... proceccors) {
		this.processors_ = proceccors;
	}

	@Override
	public void visit(final ElkAxiom elkAxiom) {
		for (int pos = 0; pos < processors_.length; pos++) {
			processors_[pos].visit(elkAxiom);
		}
	}

}
