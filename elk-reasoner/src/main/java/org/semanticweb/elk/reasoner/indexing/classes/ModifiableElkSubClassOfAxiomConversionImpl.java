
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkSubClassOfAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkSubClassOfAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkSubClassOfAxiomConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkSubClassOfAxiom>
		implements ModifiableElkSubClassOfAxiomConversion {

	ModifiableElkSubClassOfAxiomConversionImpl(ElkSubClassOfAxiom originalAxiom,
			ModifiableIndexedClassExpression subClass,
			ModifiableIndexedClassExpression superClass) {
		super(originalAxiom, subClass, superClass);
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
