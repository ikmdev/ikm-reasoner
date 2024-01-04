
package org.semanticweb.elk.reasoner.saturation.rules.contextinit;



import org.semanticweb.elk.util.collections.chains.Chain;
import org.semanticweb.elk.util.collections.chains.Chainable;

/**
 * A {@link LinkedContextInitRule} which can be added or removed to
 * {@link Chain}s of such {@link ChainableContextInitRule}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ChainableContextInitRule extends LinkedContextInitRule,
		Chainable<ChainableContextInitRule> {

	// nothing else
	
}
