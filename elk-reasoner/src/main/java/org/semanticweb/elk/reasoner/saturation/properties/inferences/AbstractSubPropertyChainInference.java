
package org.semanticweb.elk.reasoner.saturation.properties.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;

public abstract class AbstractSubPropertyChainInference extends
		AbstractObjectPropertyInference implements SubPropertyChainInference {

	private final IndexedPropertyChain subChain_, superChain_;

	public AbstractSubPropertyChainInference(IndexedPropertyChain subChain,
			IndexedPropertyChain superChain) {
		subChain_ = subChain;
		superChain_ = superChain;
	}

	public IndexedPropertyChain getSubChain() {
		return subChain_;
	}

	public IndexedPropertyChain getSuperChain() {
		return superChain_;
	}

	@Override
	public SubPropertyChain getConclusion(
			ObjectPropertyConclusion.Factory factory) {
		return factory.getSubPropertyChain(getSubChain(), getSuperChain());
	}

	@Override
	public final <O> O accept(ObjectPropertyInference.Visitor<O> visitor) {
		return accept((SubPropertyChainInference.Visitor<O>) visitor);
	}

}
