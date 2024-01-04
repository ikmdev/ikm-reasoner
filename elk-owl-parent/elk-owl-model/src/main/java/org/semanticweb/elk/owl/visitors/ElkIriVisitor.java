
package org.semanticweb.elk.owl.visitors;



import org.semanticweb.elk.owl.iris.ElkIri;

/**
 * Visitor pattern interface for instances of {@link ElkIri}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkIriVisitor<O> extends ElkFullIriVisitor<O>,
		ElkAbbreviatedIriVisitor<O> {

	// combined visitor

}
