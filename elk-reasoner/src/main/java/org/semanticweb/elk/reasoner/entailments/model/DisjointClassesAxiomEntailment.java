
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkDisjointClassesAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface DisjointClassesAxiomEntailment
		extends AxiomEntailment<ElkDisjointClassesAxiom> {

	public static interface Visitor<O> {
		O visit(DisjointClassesAxiomEntailment disjointClassesAxiomEntailment);
	}

}
