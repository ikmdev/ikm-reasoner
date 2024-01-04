
package org.semanticweb.elk.reasoner.saturation.rules;

import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A {@link RuleVisitor} which simply applies the rules that it visits to the
 * given arguments.
 * 
 * @see Rule#apply(Object, ContextPremises, ClassInferenceProducer)
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 */
public class BasicRuleVisitor extends DummyRuleVisitor<Void> {

	// logger for events
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(BasicRuleVisitor.class);

	@Override
	protected <P> Void defaultVisit(Rule<P> rule, P premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		if (LOGGER_.isTraceEnabled()) {
			LOGGER_.trace("{}: process {} by {}", premises, premise, rule);
		}
		rule.apply(premise, premises, producer);
		return null;
	}

}
