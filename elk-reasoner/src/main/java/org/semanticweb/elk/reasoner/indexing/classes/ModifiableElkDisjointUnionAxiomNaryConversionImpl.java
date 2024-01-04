
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDisjointUnionAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiomInference;

/**
 * Implements {@link ModifiableElkDisjointUnionAxiomNaryConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDisjointUnionAxiomNaryConversionImpl extends
		AbstractModifiableIndexedDisjointClassesAxiomInference<ElkDisjointUnionAxiom>
		implements ModifiableElkDisjointUnionAxiomNaryConversion {

	ModifiableElkDisjointUnionAxiomNaryConversionImpl(
			ElkDisjointUnionAxiom originalAxiom,
			ModifiableIndexedClassExpressionList disjointClasses) {
		super(originalAxiom, disjointClasses);
	}

	@Override
	public final <O> O accept(
			IndexedDisjointClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedDisjointClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
