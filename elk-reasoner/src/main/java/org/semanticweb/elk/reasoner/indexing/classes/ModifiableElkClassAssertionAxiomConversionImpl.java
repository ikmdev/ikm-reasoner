
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkClassAssertionAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkClassAssertionAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkClassAssertionAxiomConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkClassAssertionAxiom>
		implements ModifiableElkClassAssertionAxiomConversion {

	ModifiableElkClassAssertionAxiomConversionImpl(
			ElkClassAssertionAxiom originalAxiom,
			ModifiableIndexedIndividual instance,
			ModifiableIndexedClassExpression type) {
		super(originalAxiom, instance, type);
	}

	@Override
	public final <O> O accept(
			IndexedSubClassOfAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			ModifiableIndexedSubClassOfAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}	

}
