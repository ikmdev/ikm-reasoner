
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.util.collections.chains.Chainable;

public interface ChainableRule<P> extends LinkRule<P>,
		Chainable<ChainableRule<P>> {

	// nothing else
}
