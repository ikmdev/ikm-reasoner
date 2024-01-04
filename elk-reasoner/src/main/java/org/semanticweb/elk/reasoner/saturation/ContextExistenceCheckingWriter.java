
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;

/**
 * A {@link SaturationStateWriter} that does not produce conclusions if the
 * corresponding context does not exist.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <C>
 *                the type of contexts managed by this
 *                {@link SaturationStateWriter}
 */
public class ContextExistenceCheckingWriter<C extends Context> extends
		SaturationStateWriterWrap<C> {

	private final SaturationState<? extends C> state_;

	public ContextExistenceCheckingWriter(
			SaturationStateWriter<? extends C> writer,
			SaturationState<? extends C> state) {
		super(writer);
		this.state_ = state;
	}

	@Override
	public void produce(ClassInference inference) {
		Context context = state_.getContext(inference.getDestination());

		if (context != null) {
			super.produce(inference);
		}
	}

}