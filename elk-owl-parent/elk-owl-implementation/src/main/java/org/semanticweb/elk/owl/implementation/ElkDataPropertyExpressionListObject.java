
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;

/**
 * Implementation for ElkObjects that maintain a list of object property
 * expressions.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkDataPropertyExpressionListObject extends
		ElkObjectListObject<ElkDataPropertyExpression> {

	ElkDataPropertyExpressionListObject(
			List<? extends ElkDataPropertyExpression> dataPropertyExpressions) {
		super(dataPropertyExpressions);
	}

	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions() {
		return getObjects();
	}
}
