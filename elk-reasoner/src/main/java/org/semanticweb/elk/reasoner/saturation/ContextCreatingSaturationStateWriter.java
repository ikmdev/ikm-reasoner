 
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * @param <C>
 *                the type of contexts managed by this
 *                {@link SaturationStateWriter}
 */
public interface ContextCreatingSaturationStateWriter<C extends Context>
		extends SaturationStateWriter<C> {

	/**
	 * 
	 * @param root
	 * @return the context with the given root
	 */
	public C getCreateContext(IndexedContextRoot root);
}
