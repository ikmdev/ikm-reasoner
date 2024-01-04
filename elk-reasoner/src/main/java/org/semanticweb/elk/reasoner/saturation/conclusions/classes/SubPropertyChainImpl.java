
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

/**
 * An implementation of {@link SubPropertyChain}
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 * 
 * @author "Yevgeny Kazakov"
 */
public class SubPropertyChainImpl extends AbstractObjectPropertyConclusion
		implements SubPropertyChain {

	private final IndexedPropertyChain subChain_, superChain_;

	protected SubPropertyChainImpl(IndexedPropertyChain subChain,
			IndexedPropertyChain superChain) {
		subChain_ = subChain;
		superChain_ = superChain;
	}

	@Override
	public IndexedPropertyChain getSubChain() {
		return subChain_;
	}

	@Override
	public IndexedPropertyChain getSuperChain() {
		return superChain_;
	}

	@Override
	public <O> O accept(ObjectPropertyConclusion.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(SubPropertyChain.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
