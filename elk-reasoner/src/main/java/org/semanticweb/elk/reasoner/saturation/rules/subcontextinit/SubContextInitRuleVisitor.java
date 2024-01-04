
package org.semanticweb.elk.reasoner.saturation.rules.subcontextinit;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;



/**
 * A visitor pattern for {@link SubContextInitRule}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public interface SubContextInitRuleVisitor<O> {

	public O visit(PropagationInitializationRule rule,
			SubContextInitialization premise, ContextPremises premises,
			ClassInferenceProducer producer);

}
