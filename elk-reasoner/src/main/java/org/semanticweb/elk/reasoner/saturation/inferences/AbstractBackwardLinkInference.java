
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;

abstract class AbstractBackwardLinkInference extends AbstractClassInference
		implements BackwardLinkInference {

	private final IndexedObjectProperty conclusionRelation_;

	private final IndexedContextRoot conclusionSource_;

	public AbstractBackwardLinkInference(IndexedContextRoot destination,
			IndexedObjectProperty relation, IndexedContextRoot source) {
		super(destination);
		this.conclusionRelation_ = relation;
		this.conclusionSource_ = source;
	}

	public IndexedObjectProperty getConclusionRelation() {
		return conclusionRelation_;
	}

	public IndexedContextRoot getConclusionSource() {
		return conclusionSource_;
	}

	@Override
	public IndexedContextRoot getTraceRoot() {
		return conclusionSource_;
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public BackwardLink getConclusion(BackwardLink.Factory factory) {
		return factory.getBackwardLink(getDestination(), conclusionRelation_,
				conclusionSource_);
	}

	@Override
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return accept((BackwardLinkInference.Visitor<O>) visitor);
	}

}
