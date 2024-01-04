
package org.semanticweb.elk.reasoner.indexing.model;



/**
 * An {@link IndexedObjectIntersectionOf} that can be modified as a result of
 * updating the {@link ModifiableOntologyIndex} where this object is stored.
 * 
 * @author "Yevgeny Kazakov"
 *
 */
public interface ModifiableIndexedObjectIntersectionOf
		extends
			ModifiableIndexedClassExpression,
			IndexedObjectIntersectionOf {

	@Override
	ModifiableIndexedClassExpression getFirstConjunct();

	@Override
	ModifiableIndexedClassExpression getSecondConjunct();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ModifiableIndexedObjectIntersectionOf getIndexedObjectIntersectionOf(
				ModifiableIndexedClassExpression conjunctA,
				ModifiableIndexedClassExpression conjunctB);

	}

}
