 
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.DisjointSubsumerFromMemberRule;

/**
 * Implements {@link CachedIndexedClassExpressionList}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <A>
 *            the type of the {@link ElkAxiom} from which this axiom originates
 */
public class ModifiableIndexedDisjointClassesAxiomImpl<A extends ElkAxiom>
		extends
			IndexedDisjointClassesAxiomImpl<A, ModifiableIndexedClassExpressionList>
		implements
			ModifiableIndexedDisjointClassesAxiom {

	protected ModifiableIndexedDisjointClassesAxiomImpl(A originalAxiom,
			ModifiableIndexedClassExpressionList members) {
		super(originalAxiom, members);
	}

	@Override
	public boolean addOccurrence(ModifiableOntologyIndex index) {
		if (!DisjointSubsumerFromMemberRule.addRulesFor(this, index,
				getOriginalAxiom())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeOccurrence(ModifiableOntologyIndex index) {
		if (!DisjointSubsumerFromMemberRule.removeRulesFor(this, index,
				getOriginalAxiom())) {
			// revert the changes
			return false;
		}
		return true;
	}
	
	@Override
	public <O> O accept(ModifiableIndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
