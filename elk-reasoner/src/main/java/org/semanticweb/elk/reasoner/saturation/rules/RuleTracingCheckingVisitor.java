
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;

/**
 * A {@link RuleVisitor} that returns {@code true} for {@link Rule}s for which
 * {@link Rule#isTracingRule()} returns {@code true}, regardless of all other
 * parameters
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @see Rule#isTracingRule()
 *
 */
public class RuleTracingCheckingVisitor extends DummyRuleVisitor<Boolean> {

	@Override
	protected <P> Boolean defaultVisit(Rule<P> rule, P premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		return rule.isTracingRule();
	}

}
