
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.indexing.model.ElkDifferentIndividualsAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointClassesAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiomInference;

class IndexedDisjointClassesAxiomMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedDisjointClassesAxiomMatch1>
		implements IndexedDisjointClassesAxiomInference.Visitor<Void> {

	IndexedDisjointClassesAxiomMatch1InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedDisjointClassesAxiomMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ElkDifferentIndividualsAxiomNaryConversion inference) {
		factory.getElkDifferentIndividualsAxiomNaryConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkDisjointClassesAxiomNaryConversion inference) {
		factory.getElkDisjointClassesAxiomNaryConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkDisjointUnionAxiomNaryConversion inference) {
		factory.getElkDisjointUnionAxiomNaryConversionMatch1(inference, child);
		return null;
	}

}
