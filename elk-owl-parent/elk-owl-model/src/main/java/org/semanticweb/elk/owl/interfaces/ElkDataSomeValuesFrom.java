
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkDataSomeValuesFromVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Existential_Quantification_2">
 * Existential Quantification Data Property Restriction<a> in the OWL 2
 * specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataSomeValuesFrom
		extends ElkDataPropertyListRestrictionQualified {

	/**
	 * Accept an {@link ElkDataSomeValuesFromVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataSomeValuesFromVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataSomeValuesFrom}
		 * 
		 * @param range
		 *            the {@link ElkDataRange} for which the object should be
		 *            created
		 * @param first
		 *            the first {@link ElkDataPropertyExpression} for which the
		 *            object should be created
		 * @param other
		 *            other {@link ElkDataPropertyExpression}s for which the
		 *            object should be created
		 * @return an {@link ElkDataSomeValuesFrom} corresponding to the input
		 */
		public ElkDataSomeValuesFrom getDataSomeValuesFrom(
				ElkDataRange range,
				ElkDataPropertyExpression first,
				ElkDataPropertyExpression... other);

		/**
		 * Create an {@link ElkDataSomeValuesFrom}
		 * @param properties
		 *            the {@link ElkDataPropertyExpression}s for which the
		 *            object should be created
		 * @param range
		 *            the {@link ElkDataRange} for which the object should be
		 *            created
		 * 
		 * @return an {@link ElkDataSomeValuesFrom} corresponding to the input
		 */
		public ElkDataSomeValuesFrom getDataSomeValuesFrom(
				List<? extends ElkDataPropertyExpression> properties,
				ElkDataRange range);

	}

}
