
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDeclarationAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDeclarationAxiomConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDeclarationAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEntity;

/**
 * Implements {@link ModifiableElkDeclarationAxiomConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDeclarationAxiomConversionImpl extends
		AbstractModifiableIndexedDeclarationAxiomInference<ElkDeclarationAxiom>
		implements ModifiableElkDeclarationAxiomConversion {

	ModifiableElkDeclarationAxiomConversionImpl(
			ElkDeclarationAxiom originalAxiom, ModifiableIndexedEntity entity) {
		super(originalAxiom, entity);
	}

	@Override
	public final <O> O accept(
			IndexedDeclarationAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			ModifiableIndexedDeclarationAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}