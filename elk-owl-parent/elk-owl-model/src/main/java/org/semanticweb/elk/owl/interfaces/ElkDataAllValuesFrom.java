
/**
 * @author Markus Kroetzsch, Aug 8, 2011
 */
package org.semanticweb.elk.owl.interfaces;

import java.util.List;

import org.semanticweb.elk.owl.visitors.ElkDataAllValuesFromVisitor;

/**
 * Corresponds to an
 * <a href= "http://www.w3.org/TR/owl2-syntax/#Universal_Quantification_2">
 * Universal Quantification Data Property Restriction<a> in the OWL 2
 * specification.
 * 
 * @author Markus Kroetzsch
 */
public interface ElkDataAllValuesFrom
		extends ElkDataPropertyListRestrictionQualified {

	/**
	 * Accept an {@link ElkDataAllValuesFromVisitor}.
	 * 
	 * @param visitor
	 *            the visitor that can work with this object type
	 * @return the output of the visitor
	 */
	public <O> O accept(ElkDataAllValuesFromVisitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		/**
		 * Create an {@link ElkDataAllValuesFrom}.
		 * 
		 * @param range
		 *            the {@link ElkDataRange} for which the object should be
		 *            created
		 * @param first
		 *            the {@link ElkDataPropertyExpression} for which the object
		 *            should be created
		 * @param other
		 *            the {@link ElkDataPropertyExpression}s for which the
		 *            object should be created
		 * @return an {@link ElkDataAllValuesFrom} corresponding to the input
		 */
		public ElkDataAllValuesFrom getDataAllValuesFrom(ElkDataRange range,
				ElkDataPropertyExpression first,
				ElkDataPropertyExpression... other);

		/**
		 * Create an {@link ElkDataAllValuesFrom}.
		 * @param properties
		 *            the {@link ElkDataPropertyExpression}s for which the
		 *            object should be created
		 * @param range
		 *            the {@link ElkDataRange} for which the object should be
		 *            created
		 * 
		 * @return an {@link ElkDataAllValuesFrom} corresponding to the input
		 */
		public ElkDataAllValuesFrom getDataAllValuesFrom(List<? extends ElkDataPropertyExpression> properties,
				ElkDataRange range);

	}

}
