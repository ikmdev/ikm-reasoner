
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDatatypeRestriction;

/**
 * Visitor pattern interface for instances of {@link ElkDatatypeRestriction}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDatatypeRestrictionVisitor<O> {

	public O visit(ElkDatatypeRestriction expression);

}
