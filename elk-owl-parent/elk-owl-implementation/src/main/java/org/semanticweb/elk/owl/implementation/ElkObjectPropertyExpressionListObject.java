
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;

/**
 * Implementation for ElkObjects that maintain a list of object property
 * expressions.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkObjectPropertyExpressionListObject extends
		ElkObjectListObject<ElkObjectPropertyExpression> {

	ElkObjectPropertyExpressionListObject(
			List<? extends ElkObjectPropertyExpression> objectPropertyExpressions) {
		super(objectPropertyExpressions);
	}

	public List<? extends ElkObjectPropertyExpression> getObjectPropertyExpressions() {
		return getObjects();
	}
}
