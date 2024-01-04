
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkTransitiveObjectPropertyAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiomInference;

/**
 * Implements {@link ModifiableElkTransitiveObjectPropertyAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkTransitiveObjectPropertyAxiomConversionImpl extends
		AbstractModifiableIndexedSubObjectPropertyOfAxiomInference<ElkTransitiveObjectPropertyAxiom>
		implements ModifiableElkTransitiveObjectPropertyAxiomConversion {

	ModifiableElkTransitiveObjectPropertyAxiomConversionImpl(
			ElkTransitiveObjectPropertyAxiom originalAxiom,
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
