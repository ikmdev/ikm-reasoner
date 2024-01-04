 
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link RuleVisitor} which applies the rules that it visits to the
 * given arguments, producing also redundant inferences
 * 
 * @see Rule#applyTracing(Object, ContextPremises, ClassInferenceProducer)
 * 
 * @author Yevgeny Kazakov
 */
public class AllInferencesRuleVisitor extends DummyRuleVisitor<Void> {

	// logger for events
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(AllInferencesRuleVisitor.class);

	@Override
	protected <P> Void defaultVisit(Rule<P> rule, P premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		super.defaultVisit(rule, premise, premises, producer);
		if (LOGGER_.isTraceEnabled()) {
			LOGGER_.trace("{}: process {} by {} [tracing]", premises, premise, rule);
		}
		rule.applyTracing(premise, premises, producer);
		return null;
	}

}
