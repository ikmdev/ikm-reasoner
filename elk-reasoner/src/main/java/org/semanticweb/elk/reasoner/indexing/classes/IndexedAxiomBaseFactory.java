
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEntity;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiom;

public class IndexedAxiomBaseFactory implements IndexedAxiom.Factory {

	@Override
	public IndexedDisjointClassesAxiom getIndexedDisjointClassesAxiom(
			ElkAxiom originalAxiom, IndexedClassExpressionList members) {
		return new IndexedDisjointClassesAxiomImpl<ElkAxiom, IndexedClassExpressionList>(
				originalAxiom, members);
	}

	@Override
	public IndexedSubClassOfAxiom getIndexedSubClassOfAxiom(
			ElkAxiom originalAxiom, IndexedClassExpression subClass,
			IndexedClassExpression superClass) {
		return new IndexedSubClassOfAxiomImpl<ElkAxiom, IndexedClassExpression>(
				originalAxiom, subClass, superClass);
	}

	@Override
	public IndexedEquivalentClassesAxiom getIndexedEquivalentClassesAxiom(
			ElkAxiom originalAxiom, IndexedClassExpression firstMember,
			IndexedClassExpression secondMember) {
		return new IndexedEquivalentClassesAxiomImpl<ElkAxiom, IndexedClassExpression>(
				originalAxiom, firstMember, secondMember);
	}

	@Override
	public IndexedSubObjectPropertyOfAxiom getIndexedSubObjectPropertyOfAxiom(
			ElkAxiom originalAxiom, IndexedPropertyChain subPropertyChain,
			IndexedObjectProperty superProperty) {
		return new IndexedSubObjectPropertyOfAxiomImpl<ElkAxiom, IndexedPropertyChain, IndexedObjectProperty>(
				originalAxiom, subPropertyChain, superProperty);
	}

	@Override
	public IndexedObjectPropertyRangeAxiom getIndexedObjectPropertyRangeAxiom(
			ElkAxiom originalAxiom, IndexedObjectProperty property,
			IndexedClassExpression range) {
		return new IndexedObjectPropertyRangeAxiomImpl<ElkAxiom, IndexedObjectProperty, IndexedClassExpression>(
				originalAxiom, property, range);
	}

	@Override
	public IndexedDeclarationAxiom getIndexedDeclarationAxiom(
			ElkAxiom originalAxiom, IndexedEntity entity) {
		return new IndexedDeclarationAxiomImpl<ElkAxiom, IndexedEntity>(
				originalAxiom, entity);
	}

}
