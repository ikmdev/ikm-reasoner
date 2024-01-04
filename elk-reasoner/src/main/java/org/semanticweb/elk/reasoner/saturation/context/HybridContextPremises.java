
package org.semanticweb.elk.reasoner.saturation.context;



import java.util.Map;
import java.util.Set;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;
import org.semanticweb.elk.reasoner.saturation.rules.backwardlinks.LinkableBackwardLinkRule;

/**
 * {@link ContextPremises} that are build from two {@link ContextPremises}
 * objects with the same root. Local premises are taken from the first object,
 * whereas non-local premises from the second. A premise is local if it could be
 * derived starting from the root, i.e., its origin is the root of the
 * {@link ContextPremises} and sub-root origin (if it is a {@link SubClassConclusion}
 * ) is {@code null}.
 * 
 * @see ContextPremises#getRoot()
 * @see ClassConclusion#getTraceRoot()
 * @see SubClassConclusion#getTraceSubRoot()
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class HybridContextPremises implements ContextPremises {

	private final ContextPremises localPremises_;

	private final ContextPremises nonLocalPremises_;

	public HybridContextPremises(ContextPremises localPremises,
			ContextPremises nonLocalPremises) {
		this.localPremises_ = localPremises;
		this.nonLocalPremises_ = nonLocalPremises;
	}

	@Override
	public IndexedContextRoot getRoot() {
		// should be the same for local and non-local
		return localPremises_.getRoot();
	}

	@Override
	public Set<IndexedClassExpression> getComposedSubsumers() {
		// local
		return localPremises_.getComposedSubsumers();
	}

	@Override
	public Set<IndexedClassExpression> getDecomposedSubsumers() {
		// local
		return localPremises_.getDecomposedSubsumers();
	}

	@Override
	public Set<IndexedObjectProperty> getLocalReflexiveObjectProperties() {
		// local
		return localPremises_.getLocalReflexiveObjectProperties();
	}

	@Override
	public LinkableBackwardLinkRule getBackwardLinkRuleHead() {
		// local
		return localPremises_.getBackwardLinkRuleHead();
	}

	/*
	 * @Override public boolean isInconsistForDisjointnessAxiom(
	 * IndexedDisjointnessAxiom axiom) { // local return
	 * localPremises_.isInconsistForDisjointnessAxiom(axiom); }
	 */

	@Override
	public Iterable<? extends IndexedObjectSomeValuesFrom> getPropagatedSubsumers(
			IndexedPropertyChain subRoot) {
		// local
		return localPremises_.getPropagatedSubsumers(subRoot);
	}

	@Override
	public Set<? extends Integer> getSubsumerPositions(
			IndexedClassExpressionList disjoint) {
		// local
		return localPremises_.getSubsumerPositions(disjoint);
	}

	@Override
	public Map<IndexedObjectProperty, ? extends SubContextPremises> getSubContextPremisesByObjectProperty() {
		// non-local
		return nonLocalPremises_.getSubContextPremisesByObjectProperty();
	}

	@Override
	public String toString() {
		return getRoot() + "[hybrid]";
	}

}
