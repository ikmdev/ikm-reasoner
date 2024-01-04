
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.inferences.InferenceMatch;

public abstract class AbstractConclusionMatchInferenceVisitor<C> {

	final InferenceMatch.Factory factory;

	final C child;

	AbstractConclusionMatchInferenceVisitor(InferenceMatch.Factory factory,
			C child) {
		this.factory = factory;
		this.child = child;
	}

}
