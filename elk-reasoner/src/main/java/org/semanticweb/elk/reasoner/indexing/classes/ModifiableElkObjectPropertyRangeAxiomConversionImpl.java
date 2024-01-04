
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkObjectPropertyRangeAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectPropertyRangeAxiomInference;

/**
 * Implements {@link ModifiableElkObjectPropertyRangeAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkObjectPropertyRangeAxiomConversionImpl extends
		AbstractModifiableIndexedObjectPropertyRangeAxiomInference<ElkObjectPropertyRangeAxiom>
		implements ModifiableElkObjectPropertyRangeAxiomConversion {

	ModifiableElkObjectPropertyRangeAxiomConversionImpl(
			ElkObjectPropertyRangeAxiom originalAxiom,
			ModifiableIndexedObjectProperty property,
			ModifiableIndexedClassExpression range) {
		super(originalAxiom, property, range);
	}

	@Override
	public final <O> O accept(
			IndexedObjectPropertyRangeAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedObjectPropertyRangeAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
