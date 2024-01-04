
package org.semanticweb.elk.reasoner.saturation.rules.backwardlinks;



import org.semanticweb.elk.util.collections.chains.Chain;
import org.semanticweb.elk.util.collections.chains.ModifiableLink;

/**
 * A {@link LinkedBackwardLinkRule} which can be added or removed to
 * {@link Chain}s of such {@link LinkableBackwardLinkRule} rules
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface LinkableBackwardLinkRule extends LinkedBackwardLinkRule,
		ModifiableLink<LinkableBackwardLinkRule> {

	// nothing else

}
