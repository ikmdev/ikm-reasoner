
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDisjointClassesAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiomInference;

/**
 * Implements {@link ModifiableElkDisjointClassesAxiomNaryConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDisjointClassesAxiomNaryConversionImpl extends
		AbstractModifiableIndexedDisjointClassesAxiomInference<ElkDisjointClassesAxiom>
		implements ModifiableElkDisjointClassesAxiomNaryConversion {

	ModifiableElkDisjointClassesAxiomNaryConversionImpl(
			ElkDisjointClassesAxiom originalAxiom,
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
