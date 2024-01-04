
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomEquivalenceConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentClassesAxiomEquivalenceConversion;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiomInference;

class IndexedEquivalentClassesAxiomMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedEquivalentClassesAxiomMatch1>
		implements IndexedEquivalentClassesAxiomInference.Visitor<Void> {

	IndexedEquivalentClassesAxiomMatch1InferenceVisitor(InferenceMatch.Factory factory,
			IndexedEquivalentClassesAxiomMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ElkDisjointUnionAxiomEquivalenceConversion inference) {
		factory.getElkDisjointUnionAxiomEquivalenceConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkEquivalentClassesAxiomEquivalenceConversion inference) {
		factory.getElkEquivalentClassesAxiomEquivalenceConversionMatch1(
				inference, child);
		return null;
	}

}
