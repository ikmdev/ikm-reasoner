
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableIndexedSubClassOfAxiomInference}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractModifiableIndexedSubClassOfAxiomInference<A extends ElkAxiom>
		extends AbstractIndexedAxiomInference<A>
		implements ModifiableIndexedSubClassOfAxiomInference {

	private final ModifiableIndexedClassExpression subClass_, superClass_;

	AbstractModifiableIndexedSubClassOfAxiomInference(A originalAxiom,
			ModifiableIndexedClassExpression subClass,
			ModifiableIndexedClassExpression superClass) {
		super(originalAxiom);
		this.subClass_ = subClass;
		this.superClass_ = superClass;

	}

	public final ModifiableIndexedClassExpression getSubClass() {
		return this.subClass_;
	}

	public final ModifiableIndexedClassExpression getSuperClass() {
		return this.superClass_;
	}

	@Override
	public final ModifiableIndexedSubClassOfAxiom getConclusion(
			ModifiableIndexedSubClassOfAxiom.Factory factory) {
		return factory.getIndexedSubClassOfAxiom(getOriginalAxiom(),
				getSubClass(), getSuperClass());
	}

	@Override
	public IndexedSubClassOfAxiom getConclusion(
			IndexedSubClassOfAxiom.Factory factory) {
		return factory.getIndexedSubClassOfAxiom(getOriginalAxiom(),
				getSubClass(), getSuperClass());
	}

	@Override
	public final <O> O accept(IndexedAxiomInference.Visitor<O> visitor) {
		return accept((IndexedSubClassOfAxiomInference.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(ModifiableIndexedSubClassOfAxiomInference.Visitor<O>) visitor);
	}

}
