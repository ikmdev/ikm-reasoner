
package org.semanticweb.elk.reasoner.indexing.model;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.tracing.TracingInference;

/**
 * An {@link TracingInference} representing a transformation of an {@link ElkAxiom} to
 * an {@link IndexedAxiom}. An {@link ElkAxiom} can be converted to several
 * {@link IndexedAxiom}s using several such {@link IndexedAxiomInference}s
 * 
 * @see IndexedAxiom#getOriginalAxiom()
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface IndexedAxiomInference extends TracingInference {

	/**
	 * @return the {@link ElkAxiom} which has been converted by this
	 *         {@link IndexedAxiomInference}
	 */
	ElkAxiom getOriginalAxiom();

	<O> O accept(Visitor<O> visitor);

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
				IndexedDisjointClassesAxiomInference.Visitor<O>,
				IndexedSubClassOfAxiomInference.Visitor<O>,
				IndexedEquivalentClassesAxiomInference.Visitor<O>,
				IndexedSubObjectPropertyOfAxiomInference.Visitor<O>,
				IndexedObjectPropertyRangeAxiomInference.Visitor<O>,
				IndexedDeclarationAxiomInference.Visitor<O> {

		// combined interface

	}

}
