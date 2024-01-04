
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiomInference;

/**
 * Implements {@link ModifiableIndexedSubObjectPropertyOfAxiomInference}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractModifiableIndexedSubObjectPropertyOfAxiomInference<A extends ElkAxiom>
		extends AbstractIndexedAxiomInference<A>
		implements ModifiableIndexedSubObjectPropertyOfAxiomInference {

	private final ModifiableIndexedPropertyChain subPropertyChain_;

	private final ModifiableIndexedObjectProperty superProperty_;

	AbstractModifiableIndexedSubObjectPropertyOfAxiomInference(A originalAxiom,
			ModifiableIndexedPropertyChain subPropertyChain,
			ModifiableIndexedObjectProperty superProperty) {
		super(originalAxiom);
		this.subPropertyChain_ = subPropertyChain;
		this.superProperty_ = superProperty;

	}

	public final ModifiableIndexedPropertyChain getSubPropertyChain() {
		return this.subPropertyChain_;
	}

	public final ModifiableIndexedObjectProperty getSuperProperty() {
		return this.superProperty_;
	}

	@Override
	public final IndexedSubObjectPropertyOfAxiom getConclusion(
			IndexedSubObjectPropertyOfAxiom.Factory factory) {
		return factory.getIndexedSubObjectPropertyOfAxiom(getOriginalAxiom(),
				getSubPropertyChain(), getSuperProperty());
	}

	@Override
	public final ModifiableIndexedSubObjectPropertyOfAxiom getConclusion(
			ModifiableIndexedSubObjectPropertyOfAxiom.Factory factory) {
		return factory.getIndexedSubObjectPropertyOfAxiom(getOriginalAxiom(),
				getSubPropertyChain(), getSuperProperty());
	}

	@Override
	public final <O> O accept(IndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(IndexedSubObjectPropertyOfAxiomInference.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(ModifiableIndexedSubObjectPropertyOfAxiomInference.Visitor<O>) visitor);
	}

}
