
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch1Watch;
import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch2;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyPropagatedMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch.Factory;

class ClassInconsistencyMatch2InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<ClassInconsistencyMatch2>
		implements ClassInconsistencyMatch1Watch.Visitor<Void> {

	ClassInconsistencyMatch2InferenceVisitor(Factory factory,
			ClassInconsistencyMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ClassInconsistencyPropagatedMatch2 inferenceMatch2) {
		factory.getClassInconsistencyPropagatedMatch3(inferenceMatch2, child);
		return null;
	}

}
