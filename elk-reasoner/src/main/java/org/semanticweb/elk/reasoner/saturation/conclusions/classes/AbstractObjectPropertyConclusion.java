
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SaturationConclusion;
import org.semanticweb.elk.reasoner.tracing.AbstractConclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion;

/**
 * A skeleton for implementation of {@link ObjectPropertyConclusion}s.
 * 
 * @author "Yevgeny Kazakov"
 */
public abstract class AbstractObjectPropertyConclusion
		extends
			AbstractConclusion
		implements ObjectPropertyConclusion {

	@Override
	public <O> O accept(SaturationConclusion.Visitor<O> visitor) {
		return accept((ObjectPropertyConclusion.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(Conclusion.Visitor<O> visitor) {
		return accept((ObjectPropertyConclusion.Visitor<O>) visitor);
	}

}
