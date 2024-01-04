
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiomInference;

/**
 * Implements {@link ModifiableIndexedEquivalentClassesAxiomInference}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractModifiableIndexedEquivalentClassesAxiomInference<A extends ElkAxiom>
		extends AbstractIndexedAxiomInference<A>
		implements ModifiableIndexedEquivalentClassesAxiomInference {

	private final ModifiableIndexedClassExpression firstMember_, secondMember_;

	protected AbstractModifiableIndexedEquivalentClassesAxiomInference(
			A originalAxiom, ModifiableIndexedClassExpression firstMember,
			ModifiableIndexedClassExpression secondMember) {
		super(originalAxiom);
		this.firstMember_ = firstMember;
		this.secondMember_ = secondMember;
	}

	public ModifiableIndexedClassExpression getFirstMember() {
		return this.firstMember_;
	}

	public ModifiableIndexedClassExpression getSecondMember() {
		return this.secondMember_;
	}

	@Override
	public final IndexedEquivalentClassesAxiom getConclusion(
			IndexedEquivalentClassesAxiom.Factory factory) {
		return factory.getIndexedEquivalentClassesAxiom(getOriginalAxiom(),
				getFirstMember(), getSecondMember());
	}

	@Override
	public final ModifiableIndexedEquivalentClassesAxiom getConclusion(
			ModifiableIndexedEquivalentClassesAxiom.Factory factory) {
		return factory.getIndexedEquivalentClassesAxiom(getOriginalAxiom(),
				getFirstMember(), getSecondMember());
	}

	@Override
	public final <O> O accept(IndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(IndexedEquivalentClassesAxiomInference.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(ModifiableIndexedEquivalentClassesAxiomInference.Visitor<O>) visitor);
	}

}
