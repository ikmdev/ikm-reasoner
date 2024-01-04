
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkDataPropertyListRestrictionQualifiedVisitor;

/**
 * Common interface for DataSomeValuesFrom and DataAllValuesFrom restrictions
 * which can be based on a list of data (not object) property expression.
 * 
 * Arity of the datarange <i>must</i> correspond to the number of properties in
 * the list.
 * 
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *
 */
public interface ElkDataPropertyListRestrictionQualified
		extends ElkClassExpression {

	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions();

	public ElkDataRange getDataRange();

	/**
	 * Accept an {@link ElkDataPropertyListRestrictionQualifiedVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(
			ElkDataPropertyListRestrictionQualifiedVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends ElkDataAllValuesFrom.Factory,
			ElkDataSomeValuesFrom.Factory {

		// combined interface

	}
}
