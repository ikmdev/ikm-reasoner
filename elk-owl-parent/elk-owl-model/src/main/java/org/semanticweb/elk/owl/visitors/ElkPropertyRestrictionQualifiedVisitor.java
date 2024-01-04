
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRestrictionQualified;



/**
 * Visitor pattern interface for instances of
 * {@link ElkPropertyRestrictionQualified}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkPropertyRestrictionQualifiedVisitor<O> extends
		ElkCardinalityRestrictionQualifiedVisitor<O>,
		ElkDataHasValueVisitor<O>, ElkObjectAllValuesFromVisitor<O>,
		ElkObjectHasValueVisitor<O>, ElkObjectSomeValuesFromVisitor<O> {

	// combined visitor

}
