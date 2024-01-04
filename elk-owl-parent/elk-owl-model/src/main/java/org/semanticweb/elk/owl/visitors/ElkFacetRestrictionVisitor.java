
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkFacetRestriction;

/**
 * Visitor pattern interface for instances of {@link ElkFacetRestriction}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkFacetRestrictionVisitor<O> {

	O visit(ElkFacetRestriction restriction);

}
