
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;

abstract class AbstractPropagationInference extends AbstractSubClassInference
		implements PropagationInference {

	private final IndexedObjectSomeValuesFrom carry_;

	public AbstractPropagationInference(IndexedContextRoot root,
			IndexedObjectProperty relation, IndexedObjectSomeValuesFrom carry) {
		super(root, relation);
		this.carry_ = carry;
	}

	public IndexedObjectProperty getRelation() {
		return getSubDestination();
	}

	public IndexedObjectSomeValuesFrom getCarry() {
		return this.carry_;
	}

	public IndexedObjectSomeValuesFrom getConclusionCarry() {
		return getCarry();
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final Propagation getConclusion(Propagation.Factory factory) {
		return factory.getPropagation(getDestination(), getRelation(),
				getCarry());
	}

	@Override
	public final <O> O accept(SubClassInference.Visitor<O> visitor) {
		return accept((PropagationInference.Visitor<O>) visitor);
	}

}
