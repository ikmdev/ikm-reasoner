
package org.semanticweb.elk.reasoner.saturation;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;



/**
 * Creates instances of {@link ContextImpl}.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class MainContextFactory implements ContextFactory<ExtendedContext> {

	@Override
	public ExtendedContext createContext(IndexedContextRoot root) {
		return new ContextImpl(root);
	}

}
