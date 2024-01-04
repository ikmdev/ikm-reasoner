
package org.semanticweb.elk.reasoner.saturation;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link Context} with additional methods for managing them in
 * {@link SaturationState}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ExtendedContext extends Context {

	/**
	 * Marks this {@code Context} as saturated. This means that all
	 * {@link ClassConclusion}s for this {@link Context} that have the same
	 * value of {@link ClassConclusion#getTraceRoot()} as
	 * {@link Context#getRoot()}, are already computed. This method could be
	 * used from multiple threads producing consistent result (if the flag is
	 * changed concurrently by two workers, only one of them returns the
	 * previous value).
	 * 
	 * @param saturated
	 * @return the previous value of the saturation state for this
	 *         {@link Context}
	 * 
	 * @see ClassConclusion#getTraceRoot()
	 */
	boolean setSaturated(boolean saturated);

}
