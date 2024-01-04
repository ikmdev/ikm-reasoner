
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDeclarationAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDeclarationAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEntity;

abstract class AbstractModifiableIndexedDeclarationAxiomInference<A extends ElkAxiom>
		extends AbstractIndexedAxiomInference<A>
		implements ModifiableIndexedDeclarationAxiomInference {

	private final ModifiableIndexedEntity entity_;

	AbstractModifiableIndexedDeclarationAxiomInference(A originalAxiom,
			ModifiableIndexedEntity entity) {
		super(originalAxiom);
		this.entity_ = entity;
	}

	public final ModifiableIndexedEntity getEntity() {
		return this.entity_;
	}

	@Override
	public final IndexedDeclarationAxiom getConclusion(
			IndexedDeclarationAxiom.Factory factory) {
		return factory.getIndexedDeclarationAxiom(getOriginalAxiom(),
				getEntity());
	}

	@Override
	public final ModifiableIndexedDeclarationAxiom getConclusion(
			ModifiableIndexedDeclarationAxiom.Factory factory) {
		return factory.getIndexedDeclarationAxiom(getOriginalAxiom(),
				getEntity());
	}

	@Override
	public final <O> O accept(IndexedAxiomInference.Visitor<O> visitor) {
		return accept((IndexedDeclarationAxiomInference.Visitor<O>) visitor);
	}

	@Override
	public <O> O accept(ModifiableIndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(ModifiableIndexedDeclarationAxiomInference.Visitor<O>) visitor);
	}

}
