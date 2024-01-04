
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassConclusion;

/**
 * A skeleton for implementation of {@link SubClassConclusion}s.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public abstract class AbstractSubClassConclusion extends AbstractClassConclusion
		implements SubClassConclusion {

	private final IndexedObjectProperty subDestination_;

	protected AbstractSubClassConclusion(IndexedContextRoot destination,
			IndexedObjectProperty subDestination) {
		super(destination);
		this.subDestination_ = subDestination;
	}

	@Override
	public IndexedObjectProperty getSubDestination() {
		return this.subDestination_;
	}

	@Override
	public IndexedObjectProperty getTraceSubRoot() {
		return this.subDestination_;
	}

	@Override
	public <O> O accept(ClassConclusion.Visitor<O> visitor) {
		return accept((SubClassConclusion.Visitor<O>) visitor);
	}

}
