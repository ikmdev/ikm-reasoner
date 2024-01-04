
package org.semanticweb.elk.reasoner.saturation.rules.contextinit;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;



/**
 * A visitor pattern for {@link LinkedContextInitRule}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of output parameter with which this visitor works
 */
public interface LinkedContextInitRuleVisitor<O> {

	public O visit(OwlThingContextInitRule rule, ContextInitialization premise,
			ContextPremises premises, ClassInferenceProducer producer);

	public O visit(RootContextInitializationRule rule,
			ContextInitialization premise, ContextPremises premises,
			ClassInferenceProducer producer);

}
