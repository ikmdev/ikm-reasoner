
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

/**
 * Objects that can process ELK class expressions.
 * 
 * @author Peter Skocovsky
 */
public interface ElkClassExpressionProcessor {

	public void visit(ElkClassExpression elkClassExpression);

}
