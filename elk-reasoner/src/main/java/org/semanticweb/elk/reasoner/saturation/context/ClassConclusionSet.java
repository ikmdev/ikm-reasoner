
package org.semanticweb.elk.reasoner.saturation.context;



import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;

/**
 * An object containing {@link ClassConclusion}s. Every {@link ClassConclusion} is stored
 * in this {@link ClassConclusionSet} at most once.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ClassConclusionSet {

	/**
	 * Adds the given {@link ClassConclusion} to this {@link ClassConclusionSet} if it
	 * does not already contained there
	 * 
	 * @param conclusion
	 *            the {@link ClassConclusion} to be added
	 * @return {@code true} if this {@link ClassConclusionSet} has changed as a
	 *         result of this operation and {@link false} otherwise
	 */
	boolean addConclusion(ClassConclusion conclusion);

	/**
	 * Removes the given {@link ClassConclusion} from this {@link ClassConclusionSet}
	 * 
	 * @param conclusion
	 *            the {@link ClassConclusion} to be removed
	 * @return {@code true} if this {@link ClassConclusionSet} has changed as a
	 *         result of this operation and {@link false} otherwise
	 */
	boolean removeConclusion(ClassConclusion conclusion);

	/**
	 * Checks if the given {@link ClassConclusion} is contained in this
	 * {@link ClassConclusionSet}
	 * 
	 * @param conclusion
	 *            the {@link ClassConclusion} to be checked
	 * @return {@code true} if {@link ClassConclusion} is contained in this
	 *         {@link ClassConclusionSet} and {@code false} otherwise
	 */
	boolean containsConclusion(ClassConclusion conclusion);

	/**
	 * @return {@code true} if this {@link ClassConclusionSet} does not contain any
	 *         {@link ClassConclusion}. In this case,
	 *         {@link #containsConclusion(ClassConclusion)} returns {@code false} for
	 *         every input.
	 */
	boolean isEmpty();

	/**
	 * @param subRoot  
	 * @return {@code true} if the {@link SubClassConclusionSet} corresponding to the
	 *         given subRoot {@link IndexedObjectProperty} does not contain any
	 *         {@link SubClassConclusion}.
	 * 
	 * @see SubClassConclusionSet#isEmpty()
	 */
	boolean isEmpty(IndexedObjectProperty subRoot);

}
