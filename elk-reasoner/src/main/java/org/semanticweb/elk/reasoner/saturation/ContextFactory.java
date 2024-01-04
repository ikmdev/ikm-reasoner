
package org.semanticweb.elk.reasoner.saturation;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;

/**
 * Creates instances of specified subclass of {@link ExtendedContext}.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *
 * @param <EC>
 *            Instances of this type are created.
 */
public interface ContextFactory<EC extends ExtendedContext> {

	public EC createContext(IndexedContextRoot root);
}
