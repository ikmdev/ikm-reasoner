
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDifferentIndividualsAxiomNaryConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiomInference;

/**
 * Implements {@link ModifiableElkDifferentIndividualsAxiomNaryConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDifferentIndividualsAxiomNaryConversionImpl extends
		AbstractModifiableIndexedDisjointClassesAxiomInference<ElkDifferentIndividualsAxiom>
		implements ModifiableElkDifferentIndividualsAxiomNaryConversion {

	ModifiableElkDifferentIndividualsAxiomNaryConversionImpl(
			ElkDifferentIndividualsAxiom originalAxiom,
			ModifiableIndexedClassExpressionList differentIndividuals) {
		super(originalAxiom, differentIndividuals);
	}

	@Override
	public <O> O accept(
			IndexedDisjointClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			ModifiableIndexedDisjointClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
