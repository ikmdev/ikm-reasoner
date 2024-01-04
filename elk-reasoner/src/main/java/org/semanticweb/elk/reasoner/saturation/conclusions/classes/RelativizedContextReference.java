
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.Reference;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link Reference} to a {@link Context} within the given
 * {@link SaturationState} that has the same root as the {@link Context} from
 * the given {@link Reference}.
 * 
 * @author Yevgeny Kazakov
 *
 */
public class RelativizedContextReference implements Reference<Context> {

	/**
	 * The reference to a {@link Context} to be relativized
	 */
	private final Reference<? extends Context> mainContextRef_;

	/**
	 * the {@link SaturationState} used to relativize {@link Context}s
	 */
	private final SaturationState<?> state_;

	public RelativizedContextReference(Reference<? extends Context> contextReference,
			SaturationState<?> state) {
		this.mainContextRef_ = contextReference;
		this.state_ = state;
	}

	@Override
	public Context get() {
		return state_.getContext(mainContextRef_.get().getRoot());
	}

}
