
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkObjectPropertyDomainAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiomInference;

/**
 * Implements {@link ModifiableElkObjectPropertyDomainAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkObjectPropertyDomainAxiomConversionImpl extends
		AbstractModifiableIndexedSubClassOfAxiomInference<ElkObjectPropertyDomainAxiom>
		implements ModifiableElkObjectPropertyDomainAxiomConversion {

	ModifiableElkObjectPropertyDomainAxiomConversionImpl(
			ElkObjectPropertyDomainAxiom originalAxiom,
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
