
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyListRestrictionQualified;



/**
 * Visitor pattern interface for instances of
 * {@link ElkDataPropertyListRestrictionQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataPropertyListRestrictionQualifiedVisitor<O> extends
		ElkDataAllValuesFromVisitor<O>, ElkDataSomeValuesFromVisitor<O> {

	// combined visitor

}
