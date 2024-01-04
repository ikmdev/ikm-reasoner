
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedObjectComplementOf} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedObjectComplementOf
		extends
			ModifiableIndexedClassExpression,
			IndexedObjectComplementOf {

	@Override
	ModifiableIndexedClassExpression getNegated();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedObjectComplementOf getIndexedObjectComplementOf(
				ModifiableIndexedClassExpression negated);

	}

}
