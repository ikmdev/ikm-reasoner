 
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * An {@link ElkEntityVisitor} that does not do anything and returns
 * {@code null}
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class DummyElkEntityVisitor<O> extends AbstractElkEntityVisitor<O> {

	@Override
	protected O defaultVisit(ElkEntity entity) {
		// no-op
		return null;
	}

}
