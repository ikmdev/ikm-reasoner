
package org.semanticweb.elk.reasoner.saturation.rules.contradiction;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.Rule;

/**
 * A {@link Rule} applied when processing {@link ClassInconsistency}s in a
 * {@link Context}
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ContradictionRule extends Rule<ClassInconsistency> {

	public void accept(ContradictionRuleVisitor<?> visitor,
			ClassInconsistency premise, ContextPremises premises,
			ClassInferenceProducer producer);

}
