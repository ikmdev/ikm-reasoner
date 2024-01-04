
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * An {@link SaturationState.ChangeListener} that does nothing; can be used as a
 * prototype to implement other listeners
 * 
 * @author Yevgeny Kazakov
 *
 * @param <C>
 */
public class SaturationStateDummyChangeListener<C extends Context>
		implements SaturationState.ChangeListener<C> {

	@Override
	public void contextAddition(C context) {
		// does nothing by default
	}

	@Override
	public void contextsClear() {
		// does nothing by default
	}

	@Override
	public void contextMarkSaturated(C context) {
		// does nothing by default
	}

	@Override
	public void contextMarkNonSaturated(C context) {
		// does nothing by default
	}

}
