
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.ForwardLinkImpl;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;

abstract class AbstractForwardLinkInference<R extends IndexedPropertyChain>
		extends AbstractClassInference implements ForwardLinkInference {

	/**
	 * the {@link IndexedPropertyChain} in the existential restriction
	 * corresponding to this {@link ForwardLinkImpl}
	 */
	private final R forwardChain_;

	/**
	 * the {@link IndexedContextRoot} corresponding to the filler of the
	 * existential restriction corresponding to this {@link ForwardLinkImpl}
	 */
	private final IndexedContextRoot target_;

	public AbstractForwardLinkInference(IndexedContextRoot destination,
			R forwardChain, IndexedContextRoot target) {
		super(destination);
		this.forwardChain_ = forwardChain;
		this.target_ = target;
	}

	public R getChain() {
		return forwardChain_;
	}

	public IndexedContextRoot getTarget() {
		return target_;
	}

	/**
	 * @param factory
	 *            the factory for creating conclusions
	 * 
	 * @return the conclusion produced by this inference
	 */
	public final ForwardLink getConclusion(ForwardLink.Factory factory) {
		return factory.getForwardLink(getDestination(), getChain(),
				getTarget());
	}

	@Override
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return accept((ForwardLinkInference.Visitor<O>) visitor);
	}

}
