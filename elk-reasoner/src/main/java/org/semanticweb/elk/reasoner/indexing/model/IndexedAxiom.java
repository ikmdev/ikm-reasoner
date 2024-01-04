
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.tracing.Conclusion;



/**
 * Represents occurrences of an {@link ElkAxiom} in an ontology.
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 */
public interface IndexedAxiom extends IndexedObject, Conclusion {

	/**
	 * @return the {@link ElkAxiom} from which this {@link IndexedAxiom}
	 *         originates
	 */
	ElkAxiom getOriginalAxiom();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
			IndexedDisjointClassesAxiom.Factory,
			IndexedSubClassOfAxiom.Factory,
			IndexedEquivalentClassesAxiom.Factory,
			IndexedSubObjectPropertyOfAxiom.Factory,
			IndexedObjectPropertyRangeAxiom.Factory,
			IndexedDeclarationAxiom.Factory {

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
	interface Visitor<O>
			extends
				IndexedDisjointClassesAxiom.Visitor<O>,
				IndexedSubClassOfAxiom.Visitor<O>,
				IndexedEquivalentClassesAxiom.Visitor<O>,
				IndexedSubObjectPropertyOfAxiom.Visitor<O>,
				IndexedObjectPropertyRangeAxiom.Visitor<O>,
				IndexedDeclarationAxiom.Visitor<O> {

		// combined interface

	}

	<O> O accept(Visitor<O> visitor);

}
