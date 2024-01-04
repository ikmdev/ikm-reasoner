
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.interfaces.ElkDataComplementOf;

/**
 * Visitor pattern interface for instances of {@link ElkDataComplementOf}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataComplementOfVisitor<O> {

	public O visit(ElkDataComplementOf expression);

}
