
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;



/**
 * An {@link IndexedAxiom} constructed from an {@link IndexedEntity}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * [Declaration(E)]
 * </pre>
 * 
 * It is logically equivalent to the OWL axiom {@code Declaration(E)} <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * E = {@link #getEntity()}<br>
 * 
 * Represents occurrences of an {@link ElkDeclarationAxiom} in an ontology.
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedDeclarationAxiom extends IndexedAxiom {

	/**
	 * @return the {@link IndexedEntity} that represents the entity of the
	 *         {@link ElkDeclarationAxiom} represented by this
	 *         {@link IndexedDeclarationAxiom}
	 * 
	 * @see ElkDeclarationAxiom#getEntity()
	 */
	IndexedEntity getEntity();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		IndexedDeclarationAxiom getIndexedDeclarationAxiom(
				ElkAxiom originalAxiom, IndexedEntity entity);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedDeclarationAxiom axiom);

	}

}
