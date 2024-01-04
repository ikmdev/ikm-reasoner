
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAxiom;

/**
 * Visitor pattern interface for instances of {@link ElkDataPropertyAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkDataPropertyAxiomVisitor<O> extends
		ElkDataPropertyDomainAxiomVisitor<O>,
		ElkDataPropertyRangeAxiomVisitor<O>,
		ElkDisjointDataPropertiesAxiomVisitor<O>,
		ElkEquivalentDataPropertiesAxiomVisitor<O>,
		ElkFunctionalDataPropertyAxiomVisitor<O>,
		ElkSubDataPropertyOfAxiomVisitor<O> {

	// combined visitor

}
