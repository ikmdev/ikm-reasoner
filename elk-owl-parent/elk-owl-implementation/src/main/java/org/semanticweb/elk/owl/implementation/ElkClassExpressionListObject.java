
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

/**
 * Implementation for ElkObjects that maintain a list of class expressions.
 * 
 * @author Markus Kroetzsch
 */
public abstract class ElkClassExpressionListObject extends
		ElkObjectListObject<ElkClassExpression> {

	ElkClassExpressionListObject(
			List<? extends ElkClassExpression> classExpressions) {
		super(classExpressions);
	}

	public List<? extends ElkClassExpression> getClassExpressions() {
		return getObjects();
	}

}
