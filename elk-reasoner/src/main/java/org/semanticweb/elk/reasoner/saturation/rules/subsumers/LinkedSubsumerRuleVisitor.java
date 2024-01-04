
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A visitor pattern for {@link LinkedSubsumerRule}s
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public interface LinkedSubsumerRuleVisitor<O> {

	O visit(ContradictionFromNegationRule rule, IndexedClassExpression premise,
			ContextPremises premises, ClassInferenceProducer producer);

	O visit(ContradictionFromOwlNothingRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);

	O visit(DisjointSubsumerFromMemberRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);
	
	O visit(EquivalentClassFirstFromSecondRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);
	
	O visit(EquivalentClassSecondFromFirstRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);

	O visit(IndexedClassFromDefinitionRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);

	O visit(ObjectIntersectionFromFirstConjunctRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);

	O visit(ObjectIntersectionFromSecondConjunctRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);

	O visit(ObjectUnionFromDisjunctRule rule, IndexedClassExpression premise,
			ContextPremises premises, ClassInferenceProducer producer);

	O visit(PropagationFromExistentialFillerRule rule,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);

	O visit(SuperClassFromSubClassRule rule, IndexedClassExpression premise,
			ContextPremises premises, ClassInferenceProducer producer);
}
