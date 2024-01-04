 
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkEquivalentClassesAxiomEquivalenceConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiomInference;

/**
 * Implements {@link ModifiableElkEquivalentClassesAxiomEquivalenceConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkEquivalentClassesAxiomEquivalenceConversionImpl extends
		AbstractModifiableIndexedEquivalentClassesAxiomInference<ElkEquivalentClassesAxiom>
		implements ModifiableElkEquivalentClassesAxiomEquivalenceConversion {

	private final int firstMemberPosition_, secondMemberPosition_;

	ModifiableElkEquivalentClassesAxiomEquivalenceConversionImpl(
			ElkEquivalentClassesAxiom originalAxiom, int firstMemberPosition,
			int secondMemberPosition,
			ModifiableIndexedClassExpression firstMember,
			ModifiableIndexedClassExpression secondMember) {
		super(originalAxiom, firstMember, secondMember);
		this.firstMemberPosition_ = firstMemberPosition;
		this.secondMemberPosition_ = secondMemberPosition;
	}

	@Override
	public int getFirstMemberPosition() {
		return firstMemberPosition_;
	}

	@Override
	public int getSecondMemberPosition() {
		return secondMemberPosition_;
	}

	@Override
	public final <O> O accept(
			IndexedEquivalentClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedEquivalentClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
