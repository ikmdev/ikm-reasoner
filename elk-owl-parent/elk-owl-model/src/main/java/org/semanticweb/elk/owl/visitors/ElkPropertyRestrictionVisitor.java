
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkPropertyRestriction;



/**
 * Visitor pattern interface for instances of {@link ElkPropertyRestriction}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkPropertyRestrictionVisitor<O> extends
		ElkCardinalityRestrictionVisitor<O>, ElkObjectHasSelfVisitor<O>,
		ElkPropertyRestrictionQualifiedVisitor<O> {

	// combined visitor
}
