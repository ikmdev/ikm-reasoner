
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.model.IndexedComplexPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;

/**
 * An {@link IndexedPropertyChain.Visitor} that always returns {@code null}.
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class DummyIndexedPropertyChainVisitor<O> implements
		IndexedPropertyChain.Visitor<O> {

	@SuppressWarnings("unused")
	protected O defaultVisit(IndexedPropertyChain element) {
		return null;
	}

	@Override
	public O visit(IndexedObjectProperty element) {
		return defaultVisit(element);
	}

	@Override
	public O visit(IndexedComplexPropertyChain element) {
		return defaultVisit(element);
	}

}
