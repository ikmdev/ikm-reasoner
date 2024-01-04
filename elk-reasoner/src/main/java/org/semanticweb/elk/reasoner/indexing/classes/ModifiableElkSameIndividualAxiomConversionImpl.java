
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkSameIndividualAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkSameIndividualAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkSameIndividualAxiomConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkSameIndividualAxiom>
		implements ModifiableElkSameIndividualAxiomConversion {

	private final int subIndividualPosition_, superIndividualPosition_;

	ModifiableElkSameIndividualAxiomConversionImpl(
			ElkSameIndividualAxiom originalAxiom, int subIndividualPosition,
			int superIndividualPosition,
			ModifiableIndexedIndividual subIndividual,
			ModifiableIndexedIndividual superIndividual) {
		super(originalAxiom, subIndividual, superIndividual);
		this.subIndividualPosition_ = subIndividualPosition;
		this.superIndividualPosition_ = superIndividualPosition;
	}

	@Override
	public int getSubIndividualPosition() {
		return subIndividualPosition_;
	}

	@Override
	public int getSuperIndividualPosition() {
		return superIndividualPosition_;
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
