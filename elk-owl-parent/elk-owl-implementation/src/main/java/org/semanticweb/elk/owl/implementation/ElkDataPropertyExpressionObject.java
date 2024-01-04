
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;

/**
 * Implementation of ElkObjects that store one DataPropertyExpression.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkDataPropertyExpressionObject extends ElkObjectImpl {

	private final ElkDataPropertyExpression dataPropertyExpression_;

	ElkDataPropertyExpressionObject(
			ElkDataPropertyExpression dataPropertyExpression) {
		this.dataPropertyExpression_ = dataPropertyExpression;
	}

	public ElkDataPropertyExpression getProperty() {
		return dataPropertyExpression_;
	}
}
