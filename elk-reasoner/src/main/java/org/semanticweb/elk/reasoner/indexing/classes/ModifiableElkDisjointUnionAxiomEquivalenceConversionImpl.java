
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiomInference;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableElkDisjointUnionAxiomEquivalenceConversion;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiomInference;

/**
 * Implements {@link ModifiableElkDisjointUnionAxiomEquivalenceConversion}
 * 
 * @author "Yevgeny Kazakov"
 */
class ModifiableElkDisjointUnionAxiomEquivalenceConversionImpl extends
		AbstractModifiableIndexedEquivalentClassesAxiomInference<ElkDisjointUnionAxiom>
		implements ModifiableElkDisjointUnionAxiomEquivalenceConversion {

	ModifiableElkDisjointUnionAxiomEquivalenceConversionImpl(
			ElkDisjointUnionAxiom originalAxiom,
			ModifiableIndexedClass definedClass,
			ModifiableIndexedClassExpression definition) {
		super(originalAxiom, definedClass, definition);
	}

	@Override
	public final <O> O accept(
			IndexedEquivalentClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(
			ModifiableIndexedEquivalentClassesAxiomInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
