
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;

/**
 * A {@link SaturationStateWriter} that does not produce conclusions if their
 * source context is already saturated.
 * 
 * @author Pavel Klinov
 * 
 * @author "Yevgeny Kazakov"
 * @param <C> 
 */
public class SaturationCheckingWriter<C extends Context> extends
		SaturationStateWriterWrap<C> {

	private final SaturationState<? extends C> state_;

	public SaturationCheckingWriter(SaturationStateWriter<? extends C> writer,
			SaturationState<? extends C> state) {
		super(writer);
		this.state_ = state;
	}

	@Override
	public void produce(ClassInference inference) {
		Context sourceContext = state_.getContext(inference.getTraceRoot());

		if (sourceContext == null || !sourceContext.isSaturated()) {
			super.produce(inference);
		}
	}

}