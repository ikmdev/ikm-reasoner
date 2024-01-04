
package org.semanticweb.elk.matching;



import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;
import org.semanticweb.elk.reasoner.indexing.model.ElkClassAssertionAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDifferentIndividualsAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointClassesAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomOwlNothingConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomSubClassConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentClassesAxiomSubClassConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyAssertionAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyDomainAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkReflexiveObjectPropertyAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkSameIndividualAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkSubClassOfAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;

class IndexedSubClassOfAxiomMatch1InferenceVisitor extends
		AbstractConclusionMatchInferenceVisitor<IndexedSubClassOfAxiomMatch1>
		implements IndexedSubClassOfAxiomInference.Visitor<Void> {

	IndexedSubClassOfAxiomMatch1InferenceVisitor(InferenceMatch.Factory factory,
			IndexedSubClassOfAxiomMatch1 child) {
		super(factory, child);
	}

	@Override
	public Void visit(ElkClassAssertionAxiomConversion inference) {
		factory.getElkClassAssertionAxiomConversionMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(ElkDifferentIndividualsAxiomBinaryConversion inference) {
		factory.getElkDifferentIndividualsAxiomBinaryConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkDisjointClassesAxiomBinaryConversion inference) {
		factory.getElkDisjointClassesAxiomBinaryConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkDisjointUnionAxiomBinaryConversion inference) {
		factory.getElkDisjointUnionAxiomBinaryConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkDisjointUnionAxiomOwlNothingConversion inference) {
		factory.getElkDisjointUnionAxiomOwlNothingConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkDisjointUnionAxiomSubClassConversion inference) {
		factory.getElkDisjointUnionAxiomSubClassConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkEquivalentClassesAxiomSubClassConversion inference) {
		factory.getElkEquivalentClassesAxiomSubClassConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkObjectPropertyAssertionAxiomConversion inference) {
		factory.getElkObjectPropertyAssertionAxiomConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkObjectPropertyDomainAxiomConversion inference) {
		factory.getElkObjectPropertyDomainAxiomConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkReflexiveObjectPropertyAxiomConversion inference) {
		factory.getElkReflexiveObjectPropertyAxiomConversionMatch1(inference,
				child);
		return null;
	}

	@Override
	public Void visit(ElkSameIndividualAxiomConversion inference) {
		factory.getElkSameIndividualAxiomConversionMatch1(inference, child);
		return null;
	}

	@Override
	public Void visit(ElkSubClassOfAxiomConversion inference) {
		factory.getElkSubClassOfAxiomConversionMatch1(inference, child);
		return null;
	}

}
