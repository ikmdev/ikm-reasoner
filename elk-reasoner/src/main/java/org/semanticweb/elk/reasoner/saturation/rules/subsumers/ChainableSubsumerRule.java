
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.util.collections.chains.Chain;
import org.semanticweb.elk.util.collections.chains.Chainable;

/**
 * A {@link LinkedSubsumerRule} which can be added or removed to {@link Chain}s
 * of such {@link ChainableSubsumerRule}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ChainableSubsumerRule extends LinkedSubsumerRule,
		Chainable<ChainableSubsumerRule> {

	// nothing else
	
}
