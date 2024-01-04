
package org.semanticweb.elk.reasoner.saturation;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.util.concurrent.computation.InputProcessorListenerNotifyFinishedJob;

/**
 * A listener to be used with {@link ClassExpressionSaturationFactory}. The
 * listener defines functions are triggered during saturation.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <J>
 *            the type of the input jobs of
 *            {@link ClassExpressionSaturationFactory}
 */
public interface ClassExpressionSaturationListener<J extends SaturationJob<? extends IndexedContextRoot>>
		extends InputProcessorListenerNotifyFinishedJob<J> {
	
	// nothing else
}