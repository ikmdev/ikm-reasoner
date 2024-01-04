
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.util.concurrent.computation.InputProcessorListenerNotifyFinishedJob;

/**
 * A listener to be used with the {@link TransitiveReductionFactory}. The
 * listener defines functions that are triggered during transitive reduction.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <J>
 *            the type of input jobs of {@link TransitiveReductionFactory}
 * 
 * @see TransitiveReductionFactory
 */
public interface TransitiveReductionListener<J extends TransitiveReductionJob<?>>
		extends InputProcessorListenerNotifyFinishedJob<J> {
	
	// nothing else
}