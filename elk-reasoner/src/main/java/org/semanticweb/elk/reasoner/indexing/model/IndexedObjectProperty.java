 
package org.semanticweb.elk.reasoner.indexing.model;

import java.util.ArrayList;
import java.util.Collection;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;

/**
 * An {@link IndexedPropertyChain} constructed from an {@link ElkObjectProperty}
 * .<br>
 * 
 * Notation:
 * 
 * <pre>
 * R
 * </pre>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * R = {@link #getElkEntity()}<br>
 */
public interface IndexedObjectProperty
		extends
			IndexedPropertyChain,
			IndexedEntity {

	/**
	 * @return The {@link ElkObjectProperty} represented by this
	 *         {@link IndexedObjectProperty}.
	 */
	@Override
	ElkObjectProperty getElkEntity();

	/**
	 * @return The representations of all {@link ElkSubObjectPropertyExpression}
	 *         s occurring in {@link ElkSubObjectPropertyOfAxiom}, where the
	 *         super property {@link ElkObjectProperty} is represented by this
	 *         {@link IndexedObjectProperty}
	 * 
	 * @see ElkSubObjectPropertyOfAxiom#getSubObjectPropertyExpression()
	 * @see ElkSubObjectPropertyOfAxiom#getSuperObjectPropertyExpression()
	 * @see IndexedPropertyChain#getToldSuperProperties()
	 */
	ArrayList<IndexedPropertyChain> getToldSubChains();

	/**
	 * @return The {@link ElkAxiom}s responsible for the respective told sub
	 *         properties returned by {@link #getToldSubChains()}
	 * 
	 * @see IndexedPropertyChain#getToldSuperPropertiesReasons()
	 */
	ArrayList<ElkAxiom> getToldSubChainsReasons();

	/**
	 * @return The representation of ranges for all
	 *         {@link ElkObjectPropertyRangeAxiom} with the property represented
	 *         by this {@link IndexedObjectProperty}
	 * 
	 * @see ElkObjectPropertyRangeAxiom#getRange()
	 * @see ElkObjectPropertyRangeAxiom#getProperty()
	 */
	ArrayList<IndexedClassExpression> getToldRanges();

	/**
	 * @return The {@link ElkAxiom}s responsible for the respective told ranges
	 *         returned by {@link #getToldRanges()}
	 */
	ArrayList<ElkAxiom> getToldRangesReasons();

	/**
	 * @return All {@link IndexedComplexPropertyChain}s in which this
	 *         {@link IndexedObjectProperty} is a left property
	 * 
	 * @see {@link IndexedComplexPropertyChain#getFirstProperty()}
	 */
	Collection<IndexedComplexPropertyChain> getLeftChains();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedObjectProperty element);

	}

}
