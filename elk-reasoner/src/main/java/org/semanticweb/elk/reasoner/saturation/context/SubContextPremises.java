 
package org.semanticweb.elk.reasoner.saturation.context;



import java.util.Set;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;

/**
 * A finer representation for a set of {@link ClassConclusion}s that can be used as
 * premises of inference rules associated with the same sub-root
 * {@link IndexedPropertyChain} in addition to the same root
 * {@link IndexedClassExpression}. Each {@link SubContextPremises} is stored
 * within the corresponding {@link ContextPremises} for the respective sub-root
 * {@link IndexedPropertyChain}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface SubContextPremises {

	/**
	 * @return the sources of all derived {@link BackwardLink}s with relations
	 *         {@link BackwardLink#getRelation()} to be sub-root of this
	 *         {@link SubContextPremises}
	 */
	Set<IndexedContextRoot> getLinkedRoots();

	boolean isInitialized();

}
