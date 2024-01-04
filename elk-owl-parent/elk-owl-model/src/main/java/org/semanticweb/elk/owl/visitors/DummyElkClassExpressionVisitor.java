
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

/**
 * An {@link ElkClassExpressionVisitor} that does not do anything and returns
 * {@code null}
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class DummyElkClassExpressionVisitor<O> extends
		AbstractElkClassExpressionVisitor<O> {

	@Override
	protected O defaultVisit(ElkClassExpression ce) {
		// no-op
		return null;
	}

}
