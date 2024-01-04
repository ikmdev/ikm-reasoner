
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;


/**
 * A {@link ClassExpressionSaturationListener} that does nothing with finished
 * jobs
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <J>
 *            the type of the input jobs of
 *            {@link ClassExpressionSaturationFactory}
 */
public class DummyClassExpressionSaturationListener<J extends SaturationJob<? extends IndexedContextRoot>>
		implements ClassExpressionSaturationListener<J> {

	@Override
	public void notifyFinished(J job) throws InterruptedException {
		// nothing to do
	}

}
