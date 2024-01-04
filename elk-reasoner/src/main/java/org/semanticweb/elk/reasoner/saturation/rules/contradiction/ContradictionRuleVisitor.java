
package org.semanticweb.elk.reasoner.saturation.rules.contradiction;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A visitor pattern for {@link ContradictionRule}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public interface ContradictionRuleVisitor<O> {

	O visit(ContradictionPropagationRule rule, ClassInconsistency premise,
			ContextPremises premises, ClassInferenceProducer producer);

}
