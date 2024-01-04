
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedRangeFiller;

/**
 * An {@link IndexedContextRoot.Visitor} that always returns {@code null}.
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class DummyIndexedContextRootVisitor<O> extends
		DummyIndexedClassExpressionVisitor<O> implements
		IndexedContextRoot.Visitor<O> {

	@SuppressWarnings("unused")
	protected O defaultVisit(IndexedContextRoot element) {
		return null;
	}

	@Override
	protected O defaultVisit(IndexedClassExpression element) {
		return defaultVisit((IndexedContextRoot) element);
	}

	@Override
	public O visit(IndexedRangeFiller element) {
		return defaultVisit(element);
	}

}
