
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDatatype;

/**
 * Visitor pattern interface for instances of {@link ElkDatatype}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDatatypeVisitor<O> {

	public O visit(ElkDatatype expression);

}
