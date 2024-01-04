
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkObjectPropertyAssertionAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkObjectPropertyAssertionAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkObjectPropertyAssertionAxiomConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkObjectPropertyAssertionAxiom>
		implements ModifiableElkObjectPropertyAssertionAxiomConversion {

	ModifiableElkObjectPropertyAssertionAxiomConversionImpl(
			ElkObjectPropertyAssertionAxiom originalAxiom,
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
