
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObject;
import org.semanticweb.elk.reasoner.tracing.AbstractConclusion;
import org.semanticweb.elk.reasoner.tracing.Conclusion;

/**
 * Implements {@link IndexedAxiom}
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <A>
 *            the type of the {@link ElkAxiom} from which this axiom originates
 */
abstract class IndexedAxiomImpl<A extends ElkAxiom> extends AbstractConclusion
		implements
			IndexedAxiom {

	private final A originalAxiom_;

	IndexedAxiomImpl(A originalAxiom) {
		this.originalAxiom_ = originalAxiom;
	}

	@Override
	public A getOriginalAxiom() {
		return originalAxiom_;
	}

	@Override
	public final <O> O accept(IndexedObject.Visitor<O> visitor) {
		return accept((IndexedAxiom.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(Conclusion.Visitor<O> visitor) {
		return accept((IndexedAxiom.Visitor<O>) visitor);
	}

}
