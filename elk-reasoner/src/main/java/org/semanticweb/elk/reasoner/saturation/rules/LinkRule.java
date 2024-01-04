
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.util.collections.chains.Link;

public interface LinkRule<P> extends Rule<P>, Link<LinkRule<P>> {

	@Override
	public void accept(RuleVisitor<?> visitor, P premise,
			ContextPremises premises, ClassInferenceProducer producer);
}
