
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkEquivalentClassesAxiomSubClassConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkEquivalentClassesAxiomSubClassConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkEquivalentClassesAxiomSubClassConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkEquivalentClassesAxiom>
		implements ModifiableElkEquivalentClassesAxiomSubClassConversion {

	private final int subClassPosition_, superClassPosition_;

	ModifiableElkEquivalentClassesAxiomSubClassConversionImpl(
			ElkEquivalentClassesAxiom originalAxiom, int subClassPosition,
			int superClassPosition, ModifiableIndexedClassExpression subClass,
			ModifiableIndexedClassExpression superClass) {
		super(originalAxiom, subClass, superClass);
		this.subClassPosition_ = subClassPosition;
		this.superClassPosition_ = superClassPosition;
	}

	@Override
	public int getSubClassPosition() {
		return subClassPosition_;
	}

	@Override
	public int getSuperClassPosition() {
		return superClassPosition_;
	}

	@Override
	public final <O> O accept(
			IndexedSubClassOfAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedSubClassOfAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
