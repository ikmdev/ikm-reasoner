
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDataHasValue;
import org.semanticweb.elk.reasoner.indexing.model.IndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectComplementOf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectHasSelf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectUnionOf;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * An {@link IndexedClassExpression.Visitor} applying decomposition rules using a
 * given {@link SubsumerDecompositionRuleVisitor} using given
 * {@link ContextPremises} and producing conclusions using a given
 * {@link ClassInferenceProducer}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class SubsumerDecompositionVisitor implements
		IndexedClassExpression.Visitor<Void> {

	/**
	 * the rule visitor used when applying decomposition rules
	 */
	private final SubsumerDecompositionRuleVisitor<?> ruleVisitor_;

	/**
	 * the {@link ContextPremises} with which the rules are applied
	 */
	private final ContextPremises premises_;

	/**
	 * the producer for conclusions
	 */
	private final ClassInferenceProducer producer_;

	public SubsumerDecompositionVisitor(
			SubsumerDecompositionRuleVisitor<?> ruleVisitor,
			ContextPremises premises, ClassInferenceProducer producer) {
		this.ruleVisitor_ = ruleVisitor;
		this.premises_ = premises;
		this.producer_ = producer;
	}

	@Override
	public Void visit(IndexedClass element) {
		IndexedClassDecompositionRule.getInstance().accept(ruleVisitor_, element,
				premises_, producer_);
		ComposedFromDecomposedSubsumerRule.getInstance().accept(ruleVisitor_,
				element, premises_, producer_);
		return null;
	}

	@Override
	public Void visit(IndexedIndividual element) {
		ComposedFromDecomposedSubsumerRule.getInstance().accept(ruleVisitor_,
				element, premises_, producer_);
		return null;
	}

	@Override
	public Void visit(IndexedObjectComplementOf element) {
		IndexedObjectComplementOfDecomposition.getInstance().accept(
				ruleVisitor_, element, premises_, producer_);
		return null;
	}

	@Override
	public Void visit(IndexedObjectIntersectionOf element) {
		IndexedObjectIntersectionOfDecomposition.getInstance().accept(
				ruleVisitor_, element, premises_, producer_);
		return null;
	}

	@Override
	public Void visit(IndexedObjectSomeValuesFrom element) {
		IndexedObjectSomeValuesFromDecomposition.getInstance().accept(
				ruleVisitor_, element, premises_, producer_);
		return null;
	}

	@Override
	public Void visit(IndexedObjectHasSelf element) {
		IndexedObjectHasSelfDecomposition.getInstance().accept(ruleVisitor_,
				element, premises_, producer_);
		return null;
	}

	@Override
	public Void visit(IndexedObjectUnionOf element) {
		// not supported
		return null;
	}

	@Override
	public Void visit(IndexedDataHasValue element) {
		// not supported
		return null;
	}

}
