
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObjectPropertyOfAxiom;

/**
 * A {@link ModifiableIndexedAxiom.Visitor} that always returns {@code null}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <O>
 */
public class ModifiableIndexedAxiomDummyVisitor<O>
		implements ModifiableIndexedAxiom.Visitor<O> {

	@SuppressWarnings("unused")
	protected O defaultVisit(ModifiableIndexedAxiom axiom) {
		return null;
	}

	@Override
	public O visit(ModifiableIndexedDisjointClassesAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(ModifiableIndexedSubClassOfAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(ModifiableIndexedEquivalentClassesAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(ModifiableIndexedSubObjectPropertyOfAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(ModifiableIndexedObjectPropertyRangeAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(ModifiableIndexedDeclarationAxiom axiom) {
		return defaultVisit(axiom);
	}

}
