
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.ElkClassAssertionAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDeclarationAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDifferentIndividualsAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDifferentIndividualsAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointClassesAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointClassesAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomEquivalenceConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomOwlNothingConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomSubClassConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentClassesAxiomEquivalenceConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentClassesAxiomSubClassConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentObjectPropertiesAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyAssertionAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyDomainAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyRangeAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkReflexiveObjectPropertyAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkSameIndividualAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkSubClassOfAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkSubObjectPropertyOfAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ElkTransitiveObjectPropertyAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.ConclusionBaseFactory;

/**
 * Creates {@link IndexedAxiom} conclusions for the visited
 * {@link IndexedAxiomInference}s using the provided
 * {@link IndexedAxiom.Factory} and visits them using the provided
 * {@link IndexedAxiom.Visitor}.
 * 
 * @author Yevgeny Kazakov
 *
 * @param <O>
 *            the type of the output
 */
public class IndexedAxiomInferenceConclusionVisitor<O>
		implements IndexedAxiomInference.Visitor<O> {

	private final IndexedAxiom.Factory conclusionFactory_;

	private final IndexedAxiom.Visitor<O> conclusionVisitor_;

	public IndexedAxiomInferenceConclusionVisitor(
			Conclusion.Factory conclusionFactory,
			Conclusion.Visitor<O> conclusionVisitor) {
		this.conclusionFactory_ = conclusionFactory;
		this.conclusionVisitor_ = conclusionVisitor;
	}

	public IndexedAxiomInferenceConclusionVisitor(
			Conclusion.Visitor<O> conclusionVisitor) {
		this(new ConclusionBaseFactory(), conclusionVisitor);
	}

	@Override
	public O visit(ElkClassAssertionAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDeclarationAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDifferentIndividualsAxiomBinaryConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDifferentIndividualsAxiomNaryConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDisjointClassesAxiomBinaryConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDisjointClassesAxiomNaryConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDisjointUnionAxiomBinaryConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDisjointUnionAxiomEquivalenceConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDisjointUnionAxiomNaryConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDisjointUnionAxiomOwlNothingConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkDisjointUnionAxiomSubClassConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkEquivalentClassesAxiomEquivalenceConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkEquivalentClassesAxiomSubClassConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkEquivalentObjectPropertiesAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkObjectPropertyAssertionAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkObjectPropertyDomainAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkObjectPropertyRangeAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkReflexiveObjectPropertyAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkSameIndividualAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkSubClassOfAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkSubObjectPropertyOfAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

	@Override
	public O visit(ElkTransitiveObjectPropertyAxiomConversion inference) {
		return conclusionVisitor_
				.visit(inference.getConclusion(conclusionFactory_));
	}

}
