
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * An object that can modify the respective {@link SaturationState}.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <C>
 *            the type of contexts managed by this
 *            {@link SaturationStateWriter}
 */
public interface SaturationStateWriter<C extends Context>
		extends ClassInferenceProducer {

	/**
	 * @return the {@link SaturationState} modified by this
	 *         {@link SaturationStateWriter}
	 */
	public SaturationState<? extends C> getSaturationState();

	/**
	 * Removes and returns the next active {@link Context} of the
	 * {@link SaturationState}, i.e., the one which has at least one unprocessed
	 * {@link ClassConclusion}
	 * 
	 * @return the next {@link Context} of the {@link SaturationState} with
	 *         unprocessed {@link ClassConclusion} if there exists one, or
	 *         {@code null} if not
	 * 
	 * @see Context#takeToDo()
	 */
	public Context pollForActiveContext();

	/**
	 * Marks the {@link Context} with the given {@link IndexedContextRoot} as
	 * not saturated. That is, after calling of this method,
	 * {@code Context#isSaturated()} returns {@code true} for the
	 * {@link Context} returned by
	 * {@code SaturationState#getContext(IndexedContextRoot)} for the given
	 * root.
	 * 
	 * @param root
	 * @return {@code true} if the {@link Context} was marked as saturated or
	 *         {@code false} if the {@link Context} for the given
	 *         {@link IndexedContextRoot} does not exist or already marked as
	 *         saturated.
	 */
	public boolean markAsNotSaturated(IndexedContextRoot root);

	/**
	 * Removes all assignments of {@link Context}s to {@link IndexedContextRoot}
	 * s of this {@link SaturationState}. After that,
	 * {@link SaturationState#getContexts()} should be empty.
	 */
	public void resetContexts();

}
