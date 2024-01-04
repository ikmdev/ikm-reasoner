
package org.semanticweb.elk.reasoner.query;

import org.semanticweb.elk.reasoner.entailments.model.Entailment;

abstract class AbstractIndexedEntailmentQuery<E extends Entailment>
		implements IndexedEntailmentQuery<E> {

	private final E query_;

	public AbstractIndexedEntailmentQuery(final E query) {
		this.query_ = query;
	}

	@Override
	public E getQuery() {
		return query_;
	}

}
