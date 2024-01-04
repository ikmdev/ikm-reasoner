
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDisjointClassesAxiomBinaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkDisjointClassesAxiomBinaryConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDisjointClassesAxiomBinaryConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkDisjointClassesAxiom>
		implements ModifiableElkDisjointClassesAxiomBinaryConversion {

	private final int firstClassPosition_, secondClassPosition_;

	ModifiableElkDisjointClassesAxiomBinaryConversionImpl(
			ElkDisjointClassesAxiom originalAxiom, int firstClassPosition,
			int secondClassPosition,
			ModifiableIndexedObjectIntersectionOf conjunction,
			ModifiableIndexedClass bottom) {
		super(originalAxiom, conjunction, bottom);
		if (firstClassPosition == secondClassPosition) {
			throw new IllegalArgumentException(
					"Different positions expected but both = "
							+ firstClassPosition);
		}
		this.firstClassPosition_ = firstClassPosition;
		this.secondClassPosition_ = secondClassPosition;
	}

	@Override
	public int getFirstClassPosition() {
		return firstClassPosition_;
	}

	@Override
	public int getSecondClassPosition() {
		return secondClassPosition_;
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
