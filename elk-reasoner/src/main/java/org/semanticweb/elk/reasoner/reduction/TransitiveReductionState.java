
package org.semanticweb.elk.reasoner.reduction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.util.collections.ArrayHashSet;

/**
 * The intermediate state of the transitive reduction computation.
 * 
 * @param <R>
 *            the type of the root indexed class expression for which transitive
 *            reduction needs to be computed
 * 
 * @param <J>
 *            the type of the transitive reduction job
 */
class TransitiveReductionState<R extends IndexedClassExpression, J extends TransitiveReductionJob<R>> {

	/**
	 * The transitive reduction job for which this state was created
	 */
	final J initiatorJob;

	/**
	 * The (current state of the) iterator over derived subsumers of the root
	 */
	final Iterator<IndexedClassExpression> subsumerIterator;

	/**
	 * The total number of subsumers of the root
	 */
	final int subsumerCount;

	/**
	 * We collect subsumers equivalent to the root here
	 */
	final List<ElkClass> rootEquivalent;

	/**
	 * A temporary subset of subsumers used to compute direct subsumers and
	 * their equivalent classes.
	 */
	final Set<IndexedClass> prunedSubsumers;

	/**
	 * Constructing the state for the initiator job. It is required that the
	 * saturation is already computed for the root of the initiator job.
	 * 
	 * @param initiatorJob
	 * @param saturationState
	 */
	TransitiveReductionState(J initiatorJob, SaturationState<?> saturationState) {
		this.initiatorJob = initiatorJob;
		this.rootEquivalent = new ArrayList<ElkClass>(1);
		this.prunedSubsumers = new ArrayHashSet<IndexedClass>(8);
		Set<IndexedClassExpression> subsumers = saturationState.getContext(
				initiatorJob.getInput()).getComposedSubsumers();
		this.subsumerIterator = subsumers.iterator();
		this.subsumerCount = subsumers.size();
	}

}
