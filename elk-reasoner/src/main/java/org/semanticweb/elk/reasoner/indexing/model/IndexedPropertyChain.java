
package org.semanticweb.elk.reasoner.indexing.model;

import java.util.Collection;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.reasoner.saturation.properties.SaturatedPropertyChain;

/**
 * Represents occurrences of an {@link ElkSubObjectPropertyExpression} in an
 * ontology.
 * 
 * @author Frantisek Simancik
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public interface IndexedPropertyChain extends IndexedObject {

	/**
	 * @return The representations of all {@link ElkObjectProperty}s occurring
	 *         in {@link ElkSubObjectPropertyOfAxiom}s, where the sub property
	 *         {@link ElkSubObjectPropertyExpression} is represented by this
	 *         {@link IndexedPropertyChain}
	 * 
	 * @see ElkSubObjectPropertyOfAxiom#getSubObjectPropertyExpression()
	 * @see ElkSubObjectPropertyOfAxiom#getSuperObjectPropertyExpression()
	 * @see IndexedObjectProperty#getToldSubChains()
	 */
	List<IndexedObjectProperty> getToldSuperProperties();

	/**
	 * @return The {@link ElkAxiom}s responsible for the respective told super
	 *         properties returned by {@link #getToldSuperProperties()}
	 * 
	 * @see IndexedObjectProperty#getToldSubChainsReasons()
	 */
	List<ElkAxiom> getToldSuperPropertiesReasons();

	/**
	 * @return All {@link IndexedComplexPropertyChain}s in which this
	 *         {@link IndexedPropertyChain} is a suffix
	 * 
	 * @see IndexedComplexPropertyChain#getSuffixChain()
	 */
	Collection<IndexedComplexPropertyChain> getRightChains();

	/**
	 * @return The corresponding {@code SaturatedObjecProperty} assigned to this
	 *         {@link IndexedPropertyChain}; should never be {@code null}
	 */
	SaturatedPropertyChain getSaturated();

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
				IndexedObjectProperty.Visitor<O>,
				IndexedComplexPropertyChain.Visitor<O> {

		// combined interface

	}
	
	<O> O accept(Visitor<O> visitor);	

}
