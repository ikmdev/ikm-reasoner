
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SaturationConclusion;
import org.semanticweb.elk.reasoner.tracing.AbstractConclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion;

/**
 * A skeleton for implementation of {@link ClassConclusion}.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public abstract class AbstractClassConclusion extends AbstractConclusion
		implements
			ClassConclusion {

	private final IndexedContextRoot destination_;

	protected AbstractClassConclusion(IndexedContextRoot destination) {
		this.destination_ = destination;
	}

	@Override
	public IndexedContextRoot getDestination() {
		return this.destination_;
	}

	@Override
	public IndexedContextRoot getTraceRoot() {
		return this.destination_;
	}

	@Override
	public final <O> O accept(Conclusion.Visitor<O> visitor) {
		return accept((ClassConclusion.Visitor<O>) visitor);
	}
	
	@Override
	public final <O> O accept(SaturationConclusion.Visitor<O> visitor) {
		return accept((ClassConclusion.Visitor<O>) visitor);
	}
	


}
