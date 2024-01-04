
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;

/**
 * The default visitor, does nothing
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the output type of the visitor
 */
public abstract class AbstractElkEntityVisitor<O> implements
		ElkEntityVisitor<O> {

	protected abstract O defaultVisit(ElkEntity entity);

	@Override
	public O visit(ElkAnnotationProperty entity) {
		return defaultVisit(entity);
	}

	@Override
	public O visit(ElkClass entity) {
		return defaultVisit(entity);
	}

	@Override
	public O visit(ElkDataProperty entity) {
		return defaultVisit(entity);
	}

	@Override
	public O visit(ElkDatatype entity) {
		return defaultVisit(entity);
	}

	@Override
	public O visit(ElkNamedIndividual entity) {
		return defaultVisit(entity);
	}

	@Override
	public O visit(ElkObjectProperty entity) {
		return defaultVisit(entity);
	}
}