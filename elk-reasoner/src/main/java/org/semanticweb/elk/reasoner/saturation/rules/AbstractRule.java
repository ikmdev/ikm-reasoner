
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;

/**
 * A skeleton implementation of {@code Rule}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <P>
 *            the type of premises to which the rule can be applied
 */
public abstract class AbstractRule<P> implements Rule<P> {
	
	@Override
	public void applyTracing(P premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		// by default apply normally
		apply(premise, premises, producer);
	}

}
