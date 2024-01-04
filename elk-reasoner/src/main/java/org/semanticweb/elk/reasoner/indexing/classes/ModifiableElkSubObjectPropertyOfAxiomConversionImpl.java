
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkSubObjectPropertyOfAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiomInference;

/**
 * Implements {@link ModifiableElkSubObjectPropertyOfAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkSubObjectPropertyOfAxiomConversionImpl extends
		AbstractModifiableIndexedSubObjectPropertyOfAxiomInference<ElkSubObjectPropertyOfAxiom>
		implements ModifiableElkSubObjectPropertyOfAxiomConversion {

	ModifiableElkSubObjectPropertyOfAxiomConversionImpl(
			ElkSubObjectPropertyOfAxiom originalAxiom,
			ModifiableIndexedPropertyChain subPropertyChain,
			ModifiableIndexedObjectProperty superProperty) {
		super(originalAxiom, subPropertyChain, superProperty);
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
