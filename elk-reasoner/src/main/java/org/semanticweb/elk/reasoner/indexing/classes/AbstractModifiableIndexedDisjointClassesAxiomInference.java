
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiomInference;

/**
 * Implements {@link ModifiableIndexedDisjointClassesAxiomInference}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractModifiableIndexedDisjointClassesAxiomInference<A extends ElkAxiom>
		extends AbstractIndexedAxiomInference<A>
		implements ModifiableIndexedDisjointClassesAxiomInference {

	private final ModifiableIndexedClassExpressionList members_;

	AbstractModifiableIndexedDisjointClassesAxiomInference(A originalAxiom,
			ModifiableIndexedClassExpressionList members) {
		super(originalAxiom);
		this.members_ = members;
	}

	public final ModifiableIndexedClassExpressionList getMembers() {
		return members_;
	}

	@Override
	public final IndexedDisjointClassesAxiom getConclusion(
			IndexedDisjointClassesAxiom.Factory factory) {
		return factory.getIndexedDisjointClassesAxiom(getOriginalAxiom(),
				getMembers());
	}

	@Override
	public final ModifiableIndexedDisjointClassesAxiom getConclusion(
			ModifiableIndexedDisjointClassesAxiom.Factory factory) {
		return factory.getIndexedDisjointClassesAxiom(getOriginalAxiom(),
				getMembers());
	}

	@Override
	public final <O> O accept(IndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(IndexedDisjointClassesAxiomInference.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(ModifiableIndexedDisjointClassesAxiomInference.Visitor<O>) visitor);
	}

}
