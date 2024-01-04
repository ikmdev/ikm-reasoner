
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectPropertyRangeAxiomInference;

/**
 * Implements {@link ModifiableIndexedObjectPropertyRangeAxiomInference}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractModifiableIndexedObjectPropertyRangeAxiomInference<A extends ElkAxiom>
		extends AbstractIndexedAxiomInference<A>
		implements ModifiableIndexedObjectPropertyRangeAxiomInference {

	private final ModifiableIndexedObjectProperty property_;

	private final ModifiableIndexedClassExpression range_;

	AbstractModifiableIndexedObjectPropertyRangeAxiomInference(A originalAxiom,
			ModifiableIndexedObjectProperty property,
			ModifiableIndexedClassExpression range) {
		super(originalAxiom);
		this.property_ = property;
		this.range_ = range;
	}

	public final ModifiableIndexedObjectProperty getProperty() {
		return this.property_;
	}

	public final ModifiableIndexedClassExpression getRange() {
		return this.range_;
	}

	@Override
	public final <O> O accept(IndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(IndexedObjectPropertyRangeAxiomInference.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedAxiomInference.Visitor<O> visitor) {
		return accept(
				(ModifiableIndexedObjectPropertyRangeAxiomInference.Visitor<O>) visitor);
	}

	@Override
	public final IndexedObjectPropertyRangeAxiom getConclusion(
			IndexedObjectPropertyRangeAxiom.Factory factory) {
		return factory.getIndexedObjectPropertyRangeAxiom(getOriginalAxiom(),
				getProperty(), getRange());
	}

	@Override
	public final ModifiableIndexedObjectPropertyRangeAxiom getConclusion(
			ModifiableIndexedObjectPropertyRangeAxiom.Factory factory) {
		return factory.getIndexedObjectPropertyRangeAxiom(getOriginalAxiom(),
				getProperty(), getRange());
	}

}
