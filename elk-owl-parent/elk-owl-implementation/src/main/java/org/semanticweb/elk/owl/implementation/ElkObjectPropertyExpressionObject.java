
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;

/**
 * Implementation of ElkObjects that store one ObjectPropertyExpression.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkObjectPropertyExpressionObject extends ElkObjectImpl {

	private final ElkObjectPropertyExpression property_;

	ElkObjectPropertyExpressionObject(ElkObjectPropertyExpression property) {
		this.property_ = property;
	}

	public ElkObjectPropertyExpression getProperty() {
		return property_;
	}
}
