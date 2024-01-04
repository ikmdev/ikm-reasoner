
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link ForwardLink}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <R>
 *            The type of the forward relation
 */
public class ForwardLinkImpl<R extends IndexedPropertyChain> extends
		AbstractClassConclusion implements ForwardLink {

	static final Logger LOGGER_ = LoggerFactory
			.getLogger(ForwardLinkImpl.class);

	/**
	 * the {@link IndexedPropertyChain} in the existential restriction
	 * corresponding to this {@link ForwardLinkImpl}
	 */
	final R forwardChain_;

	/**
	 * the {@link IndexedContextRoot} corresponding to the filler of the
	 * existential restriction corresponding to this {@link ForwardLinkImpl}
	 */
	final IndexedContextRoot target_;

	protected ForwardLinkImpl(IndexedContextRoot root, R forwardChain,
			IndexedContextRoot target) {
		super(root);
		this.forwardChain_ = forwardChain;
		this.target_ = target;
	}

	@Override
	public R getChain() {
		return forwardChain_;
	}

	@Override
	public IndexedContextRoot getTarget() {
		return target_;
	}

	@Override
	public <O> O accept(ClassConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ForwardLink.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
