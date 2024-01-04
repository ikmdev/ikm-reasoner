
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedSubObjectPropertyOfAxiomMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentObjectPropertiesAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkSubObjectPropertyOfAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkTransitiveObjectPropertyAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiomInference;

class IndexedSubObjectPropertyOfAxiomMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedSubObjectPropertyOfAxiomMatch1>
		implements IndexedSubObjectPropertyOfAxiomInference.Visitor<Void> {

	IndexedSubObjectPropertyOfAxiomMatch1InferenceVisitor(
			InferenceMatch.Factory factory,
			IndexedSubObjectPropertyOfAxiomMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ElkEquivalentObjectPropertiesAxiomConversion inference) {
		factory.getElkEquivalentObjectPropertiesAxiomConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkSubObjectPropertyOfAxiomConversion inference) {
		factory.getElkSubObjectPropertyOfAxiomConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkTransitiveObjectPropertyAxiomConversion inference) {
		factory.getElkTransitiveObjectPropertyAxiomConversionMatch1(inference,
				child);
		return null;
	}

}
