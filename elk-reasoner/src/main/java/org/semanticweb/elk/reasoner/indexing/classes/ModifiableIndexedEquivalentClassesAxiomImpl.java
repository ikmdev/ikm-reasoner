
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.EquivalentClassFirstFromSecondRule;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.EquivalentClassSecondFromFirstRule;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.IndexedClassDecompositionRule;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.IndexedClassFromDefinitionRule;

public class ModifiableIndexedEquivalentClassesAxiomImpl<A extends ElkAxiom>
		extends
		IndexedEquivalentClassesAxiomImpl<A, ModifiableIndexedClassExpression>
		implements ModifiableIndexedEquivalentClassesAxiom {

	protected ModifiableIndexedEquivalentClassesAxiomImpl(A originalAxiom,
			ModifiableIndexedClassExpression firstMember,
			ModifiableIndexedClassExpression secondMember) {
		super(originalAxiom, firstMember, secondMember);
	}

	@Override
	public boolean addOccurrence(ModifiableOntologyIndex index) {
		ElkAxiom reason = getOriginalAxiom();
		boolean success = IndexedClassDecompositionRule.addRuleFor(this, index,
				reason);
		if (success) {
			success = IndexedClassFromDefinitionRule.addRuleFor(this, index,
					reason);
			if (success) {
				return true;
			}
			// else revert
			IndexedClassDecompositionRule.removeRuleFor(this, index, reason);
		}
		// else
		success = EquivalentClassFirstFromSecondRule.addRuleFor(this, index,
				reason);
		if (success) {
			success = EquivalentClassSecondFromFirstRule.addRuleFor(this, index,
					reason);
			if (success) {
				return true;
			}
			// else revert
			EquivalentClassFirstFromSecondRule.removeRuleFor(this, index,
					reason);
		}
		// all failed
		return false;
	}

	@Override
	public boolean removeOccurrence(ModifiableOntologyIndex index) {
		ElkAxiom reason = getOriginalAxiom();
		boolean success = IndexedClassDecompositionRule.removeRuleFor(this,
				index, reason);
		if (success) {
			success = IndexedClassFromDefinitionRule.removeRuleFor(this, index,
					reason);
			if (success) {
				return true;
			}
			// else revert
			IndexedClassDecompositionRule.addRuleFor(this, index, reason);
		}
		// else
		success = EquivalentClassFirstFromSecondRule.removeRuleFor(this, index,
				reason);
		if (success) {
			success = EquivalentClassSecondFromFirstRule.removeRuleFor(this,
					index, reason);
			if (success) {
				return true;
			}
			// else revert
			EquivalentClassFirstFromSecondRule.addRuleFor(this, index, reason);
		}
		// all failed
		return false;

	}
	
	@Override
	public <O> O accept(ModifiableIndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
