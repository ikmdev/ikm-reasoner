
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDataHasValue;
import org.semanticweb.elk.reasoner.indexing.model.IndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectComplementOf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectHasSelf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectUnionOf;

/**
 * An {@link IndexedClassExpression.Visitor} that always returns {@code null}.
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class DummyIndexedClassExpressionVisitor<O> implements
		IndexedClassExpression.Visitor<O> {

	@SuppressWarnings("unused")
	protected O defaultVisit(IndexedClassExpression element) {
		return null;
	}

	@Override
	public O visit(IndexedClass element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedDataHasValue element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedIndividual element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedObjectComplementOf element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedObjectHasSelf element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedObjectIntersectionOf element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedObjectSomeValuesFrom element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedObjectUnionOf element) {
		return defaultVisit(element);
	}

}
