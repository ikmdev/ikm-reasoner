
package org.semanticweb.elk.matching.conclusions;



public interface IndexedAxiomMatch extends ConclusionMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends IndexedDisjointClassesAxiomMatch1.Factory,
			IndexedDisjointClassesAxiomMatch2.Factory,
			IndexedSubClassOfAxiomMatch1.Factory,
			IndexedSubClassOfAxiomMatch2.Factory,
			IndexedEquivalentClassesAxiomMatch1.Factory,
			IndexedEquivalentClassesAxiomMatch2.Factory,
			IndexedSubObjectPropertyOfAxiomMatch1.Factory,
			IndexedSubObjectPropertyOfAxiomMatch2.Factory,
			IndexedObjectPropertyRangeAxiomMatch1.Factory,
			IndexedObjectPropertyRangeAxiomMatch2.Factory {

		// combined interface

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends IndexedDisjointClassesAxiomMatch1.Visitor<O>,
			IndexedDisjointClassesAxiomMatch2.Visitor<O>,
			IndexedSubClassOfAxiomMatch1.Visitor<O>,
			IndexedSubClassOfAxiomMatch2.Visitor<O>,
			IndexedEquivalentClassesAxiomMatch1.Visitor<O>,
			IndexedEquivalentClassesAxiomMatch2.Visitor<O>,
			IndexedSubObjectPropertyOfAxiomMatch1.Visitor<O>,
			IndexedSubObjectPropertyOfAxiomMatch2.Visitor<O>,
			IndexedObjectPropertyRangeAxiomMatch1.Visitor<O>,
			IndexedObjectPropertyRangeAxiomMatch2.Visitor<O> {

		// combined interface

	}

}
