
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentObjectPropertiesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkEquivalentObjectPropertiesAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiomInference;

/**
 * Implements {@link ModifiableElkEquivalentObjectPropertiesAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkEquivalentObjectPropertiesAxiomConversionImpl extends
		AbstractModifiableIndexedSubObjectPropertyOfAxiomInference<ElkEquivalentObjectPropertiesAxiom>
		implements ModifiableElkEquivalentObjectPropertiesAxiomConversion {

	private final int subPropertyPosition_, superPropertyPosition_;

	ModifiableElkEquivalentObjectPropertiesAxiomConversionImpl(
			ElkEquivalentObjectPropertiesAxiom originalAxiom,
			int subPropertyPosition, int superPropertyPosition,
			ModifiableIndexedObjectProperty subProperty,
			ModifiableIndexedObjectProperty superProperty) {
		super(originalAxiom, subProperty, superProperty);
		this.subPropertyPosition_ = subPropertyPosition;
		this.superPropertyPosition_ = superPropertyPosition;
	}

	@Override
	public int getSubPropertyPosition() {
		return subPropertyPosition_;
	}

	@Override
	public int getSuperPropertyPosition() {
		return superPropertyPosition_;
	}

	@Override
	public final <O> O accept(
			IndexedSubObjectPropertyOfAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedSubObjectPropertyOfAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
