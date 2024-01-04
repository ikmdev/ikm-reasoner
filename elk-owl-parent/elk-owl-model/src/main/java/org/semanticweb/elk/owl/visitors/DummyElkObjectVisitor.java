
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * An {@link ElkObjectVisitor} that does not do anything and returns
 * {@code null}
 * 
 * @author "Yevgeny Kazakov"
 *
 * @param <O>
 */
public class DummyElkObjectVisitor<O> extends AbstractElkObjectVisitor<O> {

	@Override
	protected O defaultVisit(ElkObject obj) {
		// no-op
		return null;
	}

}
