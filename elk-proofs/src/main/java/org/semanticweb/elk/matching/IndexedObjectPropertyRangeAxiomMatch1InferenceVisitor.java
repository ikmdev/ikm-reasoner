
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyRangeAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiomInference;

class IndexedObjectPropertyRangeAxiomMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedObjectPropertyRangeAxiomMatch1>
		implements IndexedObjectPropertyRangeAxiomInference.Visitor<Void> {

	IndexedObjectPropertyRangeAxiomMatch1InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedObjectPropertyRangeAxiomMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ElkObjectPropertyRangeAxiomConversion inference) {
		factory.getElkObjectPropertyRangeAxiomConversionMatch1(inference,
				child);
		return null;
	}

}
