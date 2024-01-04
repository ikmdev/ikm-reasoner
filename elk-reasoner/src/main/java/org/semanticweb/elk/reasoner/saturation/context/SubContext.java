
package org.semanticweb.elk.reasoner.saturation.context;

import java.util.Set;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;



/**
 * The object representing an elementary unit of computations for
 * {@link ClassConclusion}s that can be used as premises of inferences
 * associated with the same {@link IndexedPropertyChain} sub-root in addition to
 * the same {@link IndexedClassExpression} root. Each {@link SubContext} is
 * accessible from the respective {@link Context} for the corresponding
 * {@link IndexedPropertyChain} sub-root.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface SubContext extends SubContextPremises, SubClassConclusionSet {

	/**
	 * @return the representation of all derived {@link Propagation}s with
	 *         {@link Propagation#getSubDestination()} to be sub-root of
	 *         this {@link SubContextPremises}
	 */
	Set<? extends IndexedObjectSomeValuesFrom> getPropagatedSubsumers();

}
