
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.matching.inferences.PropertyRangeInheritedMatch1;

class IndexedObjectPropertyRangeAxiomMatch2InferenceVisitor
		extends
			AbstractConclusionMatchInferenceVisitor<IndexedObjectPropertyRangeAxiomMatch2>
		implements
			IndexedObjectPropertyRangeAxiomMatch1Watch.Visitor<Void> {

	IndexedObjectPropertyRangeAxiomMatch2InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedObjectPropertyRangeAxiomMatch2 child) {
		super(factory, child);
	}

	@Override
	public Void visit(PropertyRangeInheritedMatch1 inferenceMatch1) {
		factory.getPropertyRangeInheritedMatch2(inferenceMatch1, child);
		return null;
	}

	
}