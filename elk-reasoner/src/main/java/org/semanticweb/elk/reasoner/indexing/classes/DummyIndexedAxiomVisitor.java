
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiom;

/**
 * An {@link IndexedAxiom.Visitor} that always returns {@code null}.
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class DummyIndexedAxiomVisitor<O> implements IndexedAxiom.Visitor<O> {

	@SuppressWarnings("unused")
	protected O defaultVisit(IndexedAxiom axiom) {
		return null;
	}

	@Override
	public O visit(IndexedDeclarationAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(IndexedEquivalentClassesAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(IndexedDisjointClassesAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(IndexedObjectPropertyRangeAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(IndexedSubClassOfAxiom axiom) {
		return defaultVisit(axiom);
	}

	@Override
	public O visit(IndexedSubObjectPropertyOfAxiom axiom) {
		return defaultVisit(axiom);
	}

}
