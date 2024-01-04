
package org.semanticweb.elk.reasoner.indexing.conversion;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;

/**
 * Converts {@link ElkAxiom}s to (one or several) corresponding
 * {@link ModifiableIndexedAxiom}s. The converted axioms are not returned but
 * internally processed by this {@link ElkAxiomConverter}.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface ElkAxiomConverter extends ElkAxiomVisitor<Void> {

	// combined interface

}
