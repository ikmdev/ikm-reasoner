
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.tracing.AbstractTracingInference;
import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.TracingInference;

abstract class AbstractIndexedAxiomInference<A extends ElkAxiom>
		extends AbstractTracingInference implements IndexedAxiomInference {

	private final A originalAxiom_;

	AbstractIndexedAxiomInference(A originalAxiom) {
		this.originalAxiom_ = originalAxiom;
	}

	@Override
	public A getOriginalAxiom() {
		return originalAxiom_;
	}

	@Override
	public int getPremiseCount() {
		return 0;
	}

	@Override
	public Conclusion getPremise(int index, Conclusion.Factory factory) {
		return failGetPremise(index);
	}

	@Override
	public final <O> O accept(TracingInference.Visitor<O> visitor) {
		return accept((IndexedAxiomInference.Visitor<O>) visitor);
	}

}
