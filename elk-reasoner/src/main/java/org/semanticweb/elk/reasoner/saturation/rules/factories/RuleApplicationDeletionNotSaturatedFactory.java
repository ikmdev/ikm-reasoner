
package org.semanticweb.elk.reasoner.saturation.rules.factories;

import org.semanticweb.elk.reasoner.saturation.SaturationCheckingWriter;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A {@link RuleApplicationFactory} that works similarly to
 * {@link RuleApplicationDeletionFactory} except that it modifies only
 * {@link Context}s that are not saturated.
 * 
 * @see Context#isSaturated()
 * 
 * @author "Yevgeny Kazakov"
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class RuleApplicationDeletionNotSaturatedFactory extends
		RuleApplicationDeletionFactory {

	public RuleApplicationDeletionNotSaturatedFactory(
			final InterruptMonitor interrupter,
			final SaturationState<? extends Context> saturationState) {
		super(interrupter, saturationState);
	}

	@Override
	protected SaturationStateWriter<Context> getFinalWriter(SaturationStateWriter<? extends Context> writer) {
		// only write to non-saturated contexts
		return new SaturationCheckingWriter<Context>(writer, getSaturationState());
	}

}
