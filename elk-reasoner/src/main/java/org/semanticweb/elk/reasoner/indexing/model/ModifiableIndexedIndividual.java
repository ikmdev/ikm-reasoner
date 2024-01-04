
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;

/**
 * An {@link IndexedIndividual} that can be modified as a result of updating the
 * {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedIndividual
		extends
			ModifiableIndexedClassEntity,
			IndexedIndividual {

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedIndividual getIndexedIndividual(
				ElkNamedIndividual elkNamedIndividual);

	}

}
