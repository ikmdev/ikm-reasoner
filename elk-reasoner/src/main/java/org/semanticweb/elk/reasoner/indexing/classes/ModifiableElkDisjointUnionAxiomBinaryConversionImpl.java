
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDisjointUnionAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkDisjointUnionAxiomBinaryConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDisjointUnionAxiomBinaryConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkDisjointUnionAxiom>
		implements ModifiableElkDisjointUnionAxiomBinaryConversion {

	private final int firstDisjunctPosition_, secondDisjunctPosition;

	ModifiableElkDisjointUnionAxiomBinaryConversionImpl(
			ElkDisjointUnionAxiom originalAxiom, int firstDisjunctPosition,
			int secondDisjunctPosition,
			ModifiableIndexedObjectIntersectionOf conjunction,
			ModifiableIndexedClass bottom) {
		super(originalAxiom, conjunction, bottom);
		this.firstDisjunctPosition_ = firstDisjunctPosition;
		this.secondDisjunctPosition = secondDisjunctPosition;
	}

	@Override
	public int getFirstDisjunctPosition() {
		return firstDisjunctPosition_;
	}

	@Override
	public int getSecondDisjunctPosition() {
		return secondDisjunctPosition;
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
