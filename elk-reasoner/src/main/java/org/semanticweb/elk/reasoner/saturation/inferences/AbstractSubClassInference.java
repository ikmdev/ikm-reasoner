
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;

abstract class AbstractSubClassInference extends AbstractClassInference
		implements SubClassInference {

	private final IndexedObjectProperty subDestination_;

	public AbstractSubClassInference(IndexedContextRoot destination,
			IndexedObjectProperty subDestination) {
		super(destination);
		this.subDestination_ = subDestination;
	}

	@Override
	public final IndexedObjectProperty getSubDestination() {
		return this.subDestination_;
	}

	@Override
	public IndexedObjectProperty getTraceSubRoot() {
		return subDestination_;
	}

	@Override
	public final <O> O accept(ClassInference.Visitor<O> visitor) {
		return accept((SubClassInference.Visitor<O>) visitor);
	}

}
