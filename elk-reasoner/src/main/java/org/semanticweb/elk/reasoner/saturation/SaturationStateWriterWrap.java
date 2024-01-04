
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;

/**
 * An implementation of {@link SaturationStateWriter} that just mirrors all
 * methods of the given {@link SaturationStateWriter}. This class can be used
 * for convenience if some methods of a {@link SaturationStateWriter} should be
 * redefined.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <C>
 *                the type of contexts managed by this
 *                {@link SaturationStateWriter}
 */
public class SaturationStateWriterWrap<C extends Context> implements
		SaturationStateWriter<C> {

	protected final SaturationStateWriter<? extends C> mainWriter;

	public SaturationStateWriterWrap(
			SaturationStateWriter<? extends C> mainWriter) {
		this.mainWriter = mainWriter;
	}

	@Override
	public void produce(ClassInference inference) {
		mainWriter.produce(inference);
	}

	@Override
	public Context pollForActiveContext() {
		return mainWriter.pollForActiveContext();
	}

	@Override
	public boolean markAsNotSaturated(IndexedContextRoot root) {
		return mainWriter.markAsNotSaturated(root);
	}

	@Override
	public void resetContexts() {
		mainWriter.resetContexts();
	}

	@Override
	public SaturationState<? extends C> getSaturationState() {
		return mainWriter.getSaturationState();
	}

}
