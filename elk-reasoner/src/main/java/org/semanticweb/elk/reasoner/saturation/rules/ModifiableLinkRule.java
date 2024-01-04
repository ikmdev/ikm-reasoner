
package org.semanticweb.elk.reasoner.saturation.rules;



import org.semanticweb.elk.util.collections.chains.ModifiableLink;

public interface ModifiableLinkRule<E> extends LinkRule<E>,
		ModifiableLink<ModifiableLinkRule<E>> {

	// nothing else

}
