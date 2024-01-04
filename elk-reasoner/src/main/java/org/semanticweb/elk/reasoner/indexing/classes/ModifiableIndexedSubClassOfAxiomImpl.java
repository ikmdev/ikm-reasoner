
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.SuperClassFromSubClassRule;

/**
 * Implements {@link ModifiableIndexedSubClassOfAxiom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <A>
 *            the type of the {@link ElkAxiom} from which this axiom originates
 */
class ModifiableIndexedSubClassOfAxiomImpl<A extends ElkAxiom>
		extends IndexedSubClassOfAxiomImpl<A, ModifiableIndexedClassExpression>
		implements ModifiableIndexedSubClassOfAxiom {

	ModifiableIndexedSubClassOfAxiomImpl(A originalAxiom,
			ModifiableIndexedClassExpression subClass,
			ModifiableIndexedClassExpression superClass) {
		super(originalAxiom, subClass, superClass);
	}

	@Override
	public boolean addOccurrence(ModifiableOntologyIndex index) {
		return SuperClassFromSubClassRule.addRuleFor(this, index,
				getOriginalAxiom());
	}

	@Override
	public boolean removeOccurrence(ModifiableOntologyIndex index) {
		return SuperClassFromSubClassRule.removeRuleFor(this, index,
				getOriginalAxiom());
	}

	@Override
	public <O> O accept(ModifiableIndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
