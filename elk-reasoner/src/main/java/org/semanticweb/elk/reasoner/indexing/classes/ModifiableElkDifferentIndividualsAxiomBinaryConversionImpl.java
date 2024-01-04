
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDifferentIndividualsAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkDifferentIndividualsAxiomBinaryConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDifferentIndividualsAxiomBinaryConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkDifferentIndividualsAxiom>
		implements ModifiableElkDifferentIndividualsAxiomBinaryConversion {

	private final int firstIndividualPosition_, secondIndividualPosition_;

	ModifiableElkDifferentIndividualsAxiomBinaryConversionImpl(
			ElkDifferentIndividualsAxiom originalAxiom,
			int firstIndividualPosition, int secondIndividualPosition,
			ModifiableIndexedObjectIntersectionOf conjunction,
			ModifiableIndexedClassExpression bottom) {
		super(originalAxiom, conjunction, bottom);
		this.firstIndividualPosition_ = firstIndividualPosition;
		this.secondIndividualPosition_ = secondIndividualPosition;
	}

	@Override
	public int getFirstIndividualPosition() {
		return firstIndividualPosition_;
	}

	@Override
	public int getSecondIndividualPosition() {
		return secondIndividualPosition_;
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
